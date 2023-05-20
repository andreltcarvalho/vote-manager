package com.sistem.design.vote.manager.mapper;

import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.builder.AgendaTestEntityBuilder;
import com.sistem.design.vote.manager.builder.VoteTestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class VoteMapperTest {
    Vote expectedVote;

    LocalDateTime now = VoteTestEntityBuilder.getNow().plusMinutes(1);
    LocalDateTime sessionEnd = LocalDateTime.now().plusMinutes(30);

    @BeforeEach
    void setup() {
        expectedVote = new Vote()
                .setUserCpf("12112112111")
                .setAgenda(AgendaTestEntityBuilder.buildTestAgendaWithDates(now, sessionEnd));
    }

    @Test
    void testValidVote() {
        expectedVote.setVoteResult("Sim");
        Assertions.assertEquals(expectedVote, VoteTestEntityBuilder.buildSimpleVote(now, sessionEnd).setVoteTime(null));
        expectedVote.setVoteResult("Não");
        Assertions.assertEquals(expectedVote, VoteTestEntityBuilder.buildSimpleVote(now, sessionEnd).setVoteTime(null).setVoteResult("Não"));
    }

    @Test
    void testInvalidVotes() {
        Assertions.assertThrows(BusinessException.class, () -> VoteTestEntityBuilder.buildVote("SIM", "123123123", now, sessionEnd));
        Assertions.assertThrows(BusinessException.class, () -> VoteTestEntityBuilder.buildVote("NAO", "123123123", now, sessionEnd));
    }
}
