package com.sistem.design.vote.manager.app.config;

import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.service.AgendaService;
import com.sistem.design.vote.manager.app.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
@Profile("dev")
public class DevConfiguration implements CommandLineRunner {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Autowired
    AgendaService agendaService;

    @Autowired
    VoteService voteService;

    @Override
    public void run(String... args) throws InterruptedException {

        Agenda agenda = agendaService.create(
                new InsertAgendaDTO()
                        .setStartDate(LocalDateTime.now(ZoneId.systemDefault()).plusSeconds(1).format(formatter))
                        .setEndDate(LocalDateTime.now(ZoneId.systemDefault()).plusDays(1).format(formatter))
                        .setTitle("Pauta de Votação 1")
                        .setDescription("Esta pauta tem o Objetivo X"));

        Thread.sleep(1200);

        voteService.insertVote(agenda.getId(),
                new VoteDTO()
                        .setVoteResult("Sim")
                        .setCpf("12112112111")
                        .setAgendaId(agenda.getId()));

        voteService.insertVote(agenda.getId(),
                new VoteDTO()
                        .setVoteResult("Não")
                        .setCpf("52112112111")
                        .setAgendaId(agenda.getId()));
    }
}