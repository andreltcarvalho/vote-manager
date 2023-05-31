package com.sistem.design.vote.manager.steps;

import com.sistem.design.vote.manager.app.dao.AgendaDAO;
import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.service.AgendaService;
import com.sistem.design.vote.manager.builder.AgendaTestEntityBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgendaServiceSteps extends CucumberRunner {
    @Autowired
    private AgendaService agendaService;
    @Autowired
    private AgendaDAO agendaDAO;

    Agenda foundAgenda;
    Agenda createdAgenda;
    DateTimeFormatter formatter = AgendaTestEntityBuilder.formatter;

    @Given("the system has no agendas")
    public void theSystemHasNoAgendas() {
        assertTrue(agendaService.readAll().isEmpty());
    }

    @When("I create a new agenda with title {string} and description {string}")
    public void iCreateANewAgendaWithTitleAndDescription(String title, String description) {
        createdAgenda = agendaService.create(new InsertAgendaDTO()
                .setStartDate(LocalDateTime.now(ZoneId.systemDefault()).plusSeconds(2).format(formatter))
                .setEndDate(LocalDateTime.now(ZoneId.systemDefault()).plusDays(1).format(formatter))
                .setTitle(title)
                .setDescription(description));
    }

    @Then("the agenda should be created successfully")
    public void theAgendaShouldBeCreatedSuccessfully() {
        assertFalse(agendaService.readAll().isEmpty());
        assertTrue(agendaDAO.existsById(createdAgenda.getId()));
    }

    @When("I find the agenda by ID {string}")
    public void iFindTheAgendaByID(String agendaId) {
        foundAgenda = agendaService.findById(Long.valueOf(agendaId));
    }

    @Then("the agenda should be found")
    public void theAgendaShouldBeFound() {
        assertTrue(agendaDAO.existsById(foundAgenda.getId()));
    }

    @When("I delete the agenda by ID {string}")
    public void iDeleteTheAgendaByID(String agendaId) {
        agendaService.delete(Long.valueOf(agendaId));
    }

    @Then("the agenda should be deleted")
    public void theAgendaShouldBeDeleted() {
        assertFalse(agendaDAO.existsById(foundAgenda.getId()));
    }
}
