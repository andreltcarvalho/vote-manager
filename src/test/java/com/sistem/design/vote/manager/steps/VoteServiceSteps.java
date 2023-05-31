package com.sistem.design.vote.manager.steps;

import com.sistem.design.vote.manager.app.dao.VoteDAO;
import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.model.VoteId;
import com.sistem.design.vote.manager.app.service.AgendaService;
import com.sistem.design.vote.manager.app.service.VoteService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

public class VoteServiceSteps extends CucumberRunner {

    @Autowired
    VoteService voteService;
    @Autowired
    VoteDAO voteDAO;
    @Autowired
    AgendaService agendaService;

    Vote vote1;
    Vote vote2;

    @When("I vote on the agenda {string} with the result {string} and userCpf {string}")
    public void iVoteOnTheAgendaWithTheResultAndUserCpf(String agendaId, String voteResult, String userCpf) throws InterruptedException {
        Thread.sleep(3000);
        vote1 = voteService.insertVote(Long.valueOf(agendaId), new VoteDTO().setVoteResult(voteResult).setCpf(userCpf).setAgendaId(Long.valueOf(agendaId)));
    }

    @Then("the vote is inserted with success for the agenda {string} and userCpf {string}")
    public void theVoteIsInsertedWithSuccessForTheAgendaAndUserCpf(String agendaId, String cpf) {
        Assertions.assertTrue(voteDAO.existsById(new VoteId().setUserCpf(cpf).setAgenda(Long.valueOf(agendaId))));
    }
}
