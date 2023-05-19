package com.sistem.design.vote.manager.mapper;

import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.builder.TestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VoteMapperTest {
    Vote expectedVote;

    @BeforeEach
    void setup() {
        expectedVote = new Vote()
                .setUserCpf("12112112111")
                .setAgenda(TestEntityBuilder.buildSimpleTestAgenda());
    }

    @Test
    void testValidVote() {
        expectedVote.setVoteResult("Sim");
        Assertions.assertEquals(expectedVote, TestEntityBuilder.buildSimpleVote().setVoteTime(null));
        expectedVote.setVoteResult("Não");
        Assertions.assertEquals(expectedVote, TestEntityBuilder.buildSimpleVote().setVoteTime(null).setVoteResult("Não"));
    }

    @Test
    void testInvalidVotes() {
        Assertions.assertThrows(BusinessException.class, () -> TestEntityBuilder.buildVote("SIM", "123123123"));
        Assertions.assertThrows(BusinessException.class, () -> TestEntityBuilder.buildVote("NAO", "123123123"));
    }
}
