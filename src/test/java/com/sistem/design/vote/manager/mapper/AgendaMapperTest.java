package com.sistem.design.vote.manager.mapper;

import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.builder.AgendaTestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AgendaMapperTest {
    Agenda expectedAgenda;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    LocalDateTime now = AgendaTestEntityBuilder.getNow().plusMinutes(1);
    LocalDateTime sessionEnd = AgendaTestEntityBuilder.getNow().plusMinutes(30);


    @BeforeEach
    void setup() {
        expectedAgenda = new Agenda()
                .setId(1L)
                .setVotes(new ArrayList<>())
                .setStartDate(now.withNano(0))
                .setEndDate(sessionEnd.withNano(0));
    }


    @Test
    void testValidAgendaCreation() {
        Assertions.assertEquals(expectedAgenda, AgendaTestEntityBuilder.buildTestAgendaWithDates(now, sessionEnd));
    }

    @Test
    void testValidCreationtWithStartDateOnly() {
        expectedAgenda.setEndDate(expectedAgenda.getStartDate().plusMinutes(1));
        Assertions.assertEquals(expectedAgenda, AgendaTestEntityBuilder.buildTestAgendaWithStartDate(now));
    }
}
