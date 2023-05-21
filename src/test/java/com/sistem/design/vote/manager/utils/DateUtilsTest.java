package com.sistem.design.vote.manager.utils;

import com.sistem.design.vote.manager.app.exception.InvalidDateException;
import com.sistem.design.vote.manager.app.utils.DateUtils;
import com.sistem.design.vote.manager.builder.VoteTestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateUtilsTest {
    LocalDateTime now;

    @BeforeEach
    void setup() {
        now = VoteTestEntityBuilder.getNow();
    }

    @Test
    public void testValidParse() {
        LocalDateTime expectedDate = LocalDateTime.of(2023, 5, 10, 15, 30, 0);
        Assertions.assertEquals(expectedDate, DateUtils.parseDateFromString("10-05-2023 15:30:00"));
    }

    @Test
    public void testInvalidParse() {
        String dateStr = "2023-05-10 15:30:00";
        Assertions.assertThrows(DateTimeParseException.class, () -> DateUtils.parseDateFromString(dateStr));
    }

    @Test
    public void testEmptyAndNullParse() {
        Assertions.assertThrows(DateTimeParseException.class, () -> DateUtils.parseDateFromString(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> DateUtils.parseDateFromString(null), "Date can't be empty");
    }

    @Test
    public void testCreationDates() {
        Assertions.assertDoesNotThrow(() -> DateUtils.validateCreationDates(now.plusMinutes(1), now.plusMinutes(5)));
        Assertions.assertDoesNotThrow(() -> DateUtils.validateCreationDates(now.plusMinutes(1), now.plusHours(1)));
        Assertions.assertDoesNotThrow(() -> DateUtils.validateCreationDates(now.plusMinutes(1), now.plusDays(1)));
    }

    @Test
    public void testInvalidCreationDate() {
        Throwable message = Assertions.assertThrows(InvalidDateException.class, () -> DateUtils.validateCreationDates(now.minusMinutes(5), now.plusMinutes(1)));
        Assertions.assertTrue(message.getMessage().contains("can't happen before now"));
        message = Assertions.assertThrows(InvalidDateException.class, () -> DateUtils.validateCreationDates(now,now.minusHours(1)));
        Assertions.assertTrue(message.getMessage().contains("can't happen before the start Date"));
    }
}