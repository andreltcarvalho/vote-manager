package com.sistem.design.vote.manager.utils;

import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.utils.BusinessUtils;
import com.sistem.design.vote.manager.builder.AgendaTestEntityBuilder;
import com.sistem.design.vote.manager.builder.VoteTestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class BusinessUtilsTest {

    LocalDateTime now = VoteTestEntityBuilder.getNow();
    LocalDateTime sessionEnd = VoteTestEntityBuilder.getNow().plusMinutes(30);

    @Test
    void voteAfterTheAgendaHasClosed() {
        Assertions.assertThrows(BusinessException.class, () -> BusinessUtils.validateIfTheSessionIsOpen(VoteTestEntityBuilder.buildSimpleVote(now.plusMinutes(15), sessionEnd.plusMinutes(18)).setVoteTime(now.plusMinutes(35))));
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
}
