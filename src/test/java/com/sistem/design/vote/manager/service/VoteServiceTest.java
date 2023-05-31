package com.sistem.design.vote.manager.service;


import com.sistem.design.vote.manager.app.ApiApplication;
import com.sistem.design.vote.manager.app.dao.VoteDAO;
import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.model.VoteId;
import com.sistem.design.vote.manager.app.service.AgendaService;
import com.sistem.design.vote.manager.app.service.VoteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@Rollback(value = false)
public class VoteServiceTest {
    Agenda agenda;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    Vote vote1;
    Vote vote2;
    @Autowired
    private AgendaService agendaService;
    @Autowired
    private VoteService voteService;

    @Autowired
    VoteDAO voteDAO;


    @BeforeAll
    void setup() throws InterruptedException {
        agenda = agendaService.create(new InsertAgendaDTO().setStartDate(LocalDateTime.now(ZoneId.systemDefault()).plusSeconds(2).format(formatter)).setEndDate(LocalDateTime.now(ZoneId.systemDefault()).plusDays(1).format(formatter)).setTitle("Pauta de Votação 1").setDescription("Esta pauta tem o Objetivo X"));
        Thread.sleep(2400);
    }

    @Test
    @Transactional
    @Order(1)
    void insertVotes() {
        assertTrue(voteDAO.findAll().isEmpty());
        vote1 = voteService.insertVote(agenda.getId(), new VoteDTO().setVoteResult("Sim").setCpf("12112112111").setAgendaId(agenda.getId()));
        vote2 = voteService.insertVote(agenda.getId(), new VoteDTO().setVoteResult("Não").setCpf("52112112111").setAgendaId(agenda.getId()));
        assertEquals(2, voteDAO.findAll().size());
    }

    @Test
    @Transactional
    @Order(2)
    void testFindById() {
        VoteId voteId1 = new VoteId().setAgenda(agenda.getId()).setUserCpf(vote1.getUserCpf());
        VoteId voteId2 = new VoteId().setAgenda(agenda.getId()).setUserCpf(vote2.getUserCpf());

        assertTrue(voteDAO.existsById(voteId1));
        assertTrue(voteDAO.existsById(voteId2));

        vote1 = voteDAO.findById(voteId1).orElse(null);
        vote2 = voteDAO.findById(voteId2).orElse(null);

        assertNotNull(vote1);
        assertNotNull(vote2);

        List<Vote> votes = voteDAO.findVotesWithAgenda(List.of(vote1.getId(), vote2.getId()));

        assertEquals(vote1, votes.get(0));
        assertEquals(vote2, votes.get(1));
    }

    @Test
    @Transactional
    @Order(3)
    void duplicateVote() {
        assertThrows(BusinessException.class, () -> vote1 = voteService.insertVote(agenda.getId(), new VoteDTO().setVoteResult("Sim").setCpf("12112112111").setAgendaId(agenda.getId())));
    }
}