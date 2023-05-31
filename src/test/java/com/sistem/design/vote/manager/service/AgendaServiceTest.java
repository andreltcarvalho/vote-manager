package com.sistem.design.vote.manager.service;

import com.sistem.design.vote.manager.app.ApiApplication;
import com.sistem.design.vote.manager.app.dao.AgendaDAO;
import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.exception.ResourceNotFoundException;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.service.AgendaService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AgendaServiceTest {
    Agenda agenda;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    @Autowired
    private AgendaService agendaService;
    @Autowired
    AgendaDAO agendaDAO;

    @Test
    @Order(0)
    void create() {
        assertTrue(agendaService.readAll().isEmpty());
        agenda = agendaService.create(new InsertAgendaDTO()
                .setStartDate(LocalDateTime.now(ZoneId.systemDefault()).plusSeconds(2).format(formatter))
                .setEndDate(LocalDateTime.now(ZoneId.systemDefault()).plusDays(1).format(formatter))
                .setTitle("Pauta de Votação 1")
                .setDescription("Esta pauta tem o Objetivo X"));
        assertTrue(agendaDAO.existsById(agenda.getId()));
    }

    @Test
    @Order(1)
    void testFindById() {
        Agenda foundAgenda = agendaService.findById(agenda.getId());
        assertEquals(agenda, foundAgenda);
    }

    @Test
    @Order(2)
    void testFindAll() {
        List<Agenda> agendas = agendaService.readAll();
        assertEquals(agenda, agendas.get(0));
        assertEquals(1, agendas.size());
    }

    @Test
    @Order(3)
    void testDelete() {
        agendaService.delete(agenda.getId());
        assertFalse(agendaDAO.existsById(agenda.getId()));
        assertThrows(ResourceNotFoundException.class, () -> agendaService.findById(agenda.getId()));
    }

    @Test
    @Order(4)
    void testDeleteWithNoAgenda() {
        assertThrows(ResourceNotFoundException.class, () -> agendaService.delete(5L));
    }
}