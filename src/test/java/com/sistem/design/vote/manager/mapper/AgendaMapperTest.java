package com.sistem.design.vote.manager.mapper;

import com.sistem.design.vote.manager.app.dto.AgendaResultDTO;
import com.sistem.design.vote.manager.app.mapper.AgendaMapper;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.builder.AgendaTestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.sistem.design.vote.manager.app.utils.Constants.CLOSED;
import static com.sistem.design.vote.manager.app.utils.Constants.NOT_APPROVED;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(expectedAgenda, AgendaTestEntityBuilder.buildTestAgendaWithDates(now, sessionEnd));
    }

    @Test
    void testValidCreationtWithStartDateOnly() {
        expectedAgenda.setEndDate(expectedAgenda.getStartDate().plusMinutes(1));
        assertEquals(expectedAgenda, AgendaTestEntityBuilder.buildTestAgendaWithStartDate(now));
    }

    @Test
    void testAgendaReadDTO() {
        Agenda agenda = AgendaTestEntityBuilder.buildTestAgendaWithStartDate(now);
        AgendaResultDTO expectedDTO = new AgendaResultDTO().setResult(NOT_APPROVED).setAgenda(agenda).setStatus(CLOSED);
        assertEquals(expectedDTO,AgendaMapper.toAgendaResultDTO(agenda));
    }
}
