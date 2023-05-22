package com.sistem.design.vote.manager.utils;

import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.utils.BusinessUtils;
import com.sistem.design.vote.manager.builder.AgendaTestEntityBuilder;
import com.sistem.design.vote.manager.builder.VoteTestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.sistem.design.vote.manager.app.utils.Constants.*;

public class BusinessUtilsTest {

    LocalDateTime now = VoteTestEntityBuilder.getNow();
    LocalDateTime sessionEnd = VoteTestEntityBuilder.getNow().plusMinutes(30);

    @Test
    void voteAfterTheAgendaHasClosed() {
        Assertions.assertThrows(BusinessException.class, () -> BusinessUtils.validateIfTheSessionIsOpen(VoteTestEntityBuilder.buildSimpleVote(now.plusMinutes(15), sessionEnd.plusMinutes(18)).setVoteTime(sessionEnd.plusMinutes(35))));
    }

    @Test
    void voteBeforeTheAgendaIsOpen() {
        Assertions.assertThrows(BusinessException.class, () -> BusinessUtils.validateIfTheSessionIsOpen(VoteTestEntityBuilder.buildSimpleVote(now.plusMinutes(15), sessionEnd.plusMinutes(18))));
    }

    @Test
    void testIfSessionIsOpen() throws InterruptedException {
        Assertions.assertFalse(BusinessUtils.isSessionOpen(AgendaTestEntityBuilder.buildTestAgendaWithStartDate(now.plusSeconds(1))));
        Agenda agenda = AgendaTestEntityBuilder.buildTestAgendaWithStartDate(now.plusSeconds(1));
        Thread.sleep(1000);
        Assertions.assertTrue(BusinessUtils.isSessionOpen(agenda));
    }

    @Test
    void getStatusFromAgenda() throws InterruptedException {
        Assertions.assertEquals(CLOSED, BusinessUtils.getStatusFromAgenda(AgendaTestEntityBuilder.buildTestAgendaWithStartDate(now.plusYears(1))));
        Agenda agenda = AgendaTestEntityBuilder.buildTestAgendaWithStartDate(now.plusSeconds(1));
        Thread.sleep(1000);
        Assertions.assertEquals(OPEN, BusinessUtils.getStatusFromAgenda(agenda));
    }

    @Test
    void getResultFromStatus() {
        Assertions.assertEquals(ON_GOING, BusinessUtils.getResultByStatus(OPEN, APPROVED));
        Assertions.assertEquals(ON_GOING, BusinessUtils.getResultByStatus(OPEN, NOT_APPROVED));
        Assertions.assertEquals(APPROVED, BusinessUtils.getResultByStatus(CLOSED, APPROVED));
        Assertions.assertEquals(NOT_APPROVED, BusinessUtils.getResultByStatus(CLOSED, NOT_APPROVED));
    }

    @Test
    void getResultFromVoteList() {
        Assertions.assertEquals(NOT_APPROVED, BusinessUtils.getResultFromVoteList(new Agenda().setVotes(new ArrayList<>())));
        Assertions.assertEquals(APPROVED, BusinessUtils.getResultFromVoteList(new Agenda()
                .setVotes(List.of(new Vote().setVoteResult("Sim")))));
        Assertions.assertEquals(NOT_APPROVED, BusinessUtils.getResultFromVoteList(new Agenda()
                .setVotes(List.of(new Vote().setVoteResult("Sim"), new Vote().setVoteResult("Não")))));
    }
}
