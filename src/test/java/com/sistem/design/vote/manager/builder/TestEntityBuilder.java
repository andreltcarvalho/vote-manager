package com.sistem.design.vote.manager.builder;

import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.mapper.AgendaMapper;
import com.sistem.design.vote.manager.app.mapper.VoteMapper;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.model.Vote;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestEntityBuilder {
    private TestEntityBuilder() {

    }

    public static Agenda buildSimpleTestAgenda() {
        return buildTestAgendaWithDates("19-05-2023 19:00:00", "19-05-2023 19:30:00");
    }

    public static Agenda buildTestAgendaWithStartDate(String startDate) {
        return buildTestAgendaWithDates(startDate, "");
    }

    public static Agenda buildTestAgendaWithDates(String startDate, String endDate) {
        return AgendaMapper.getFromInsertDTO(new InsertAgendaDTO().setStartDate(startDate).setEndDate(endDate)).setId(1L);
    }

    public static LocalDateTime getNow() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    public static Vote buildSimpleVote() {
        return buildVote("Sim", "12112112111");
    }

    public static Vote buildVote(String voteResult, String cpf) {
        return VoteMapper.getVoteFromInsertDTO(new VoteDTO().setAgendaId(1L).setVoteResult(voteResult).setCpf(cpf)).setAgenda(buildSimpleTestAgenda());
    }
}
