package com.sistem.design.vote.manager.mapper;

import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.builder.TestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AgendaMapperTest {
    Agenda expectedAgenda;

    @BeforeEach
    void setup() {
        expectedAgenda = new Agenda()
                .setId(1L)
                .setVotes(new ArrayList<>())
                .setStartDate(LocalDateTime.of(2023, 5, 19, 19, 0, 0))
                .setEndDate(LocalDateTime.of(2023, 5, 19, 19, 30, 0));
    }

    @Test
    void testInsertValid() {
        Assertions.assertEquals(expectedAgenda, TestEntityBuilder.buildSimpleTestAgenda());
    }

    @Test
    void testInsertWithStartDateAndEndDate() {
        Assertions.assertEquals(expectedAgenda, TestEntityBuilder.buildTestAgendaWithDates("19-05-2023 19:00:00", "19-05-2023 19:30:00"));
    }

    @Test
    void testInsertWithOnlyStartDate() {
        expectedAgenda.setEndDate(expectedAgenda.getStartDate().plusMinutes(1));
        Assertions.assertEquals(expectedAgenda, TestEntityBuilder.buildTestAgendaWithStartDate("19-05-2023 19:00:00"));
    }
}
