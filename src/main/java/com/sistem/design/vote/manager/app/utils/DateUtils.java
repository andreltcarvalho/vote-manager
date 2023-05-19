package com.sistem.design.vote.manager.app.utils;

import com.sistem.design.vote.manager.app.exception.InvalidDateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private DateUtils() {
    }

    public static LocalDateTime parseDateFromString(String date) {
        Assert.notNull(date,"Date can't be empty");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    public static LocalDateTime setEndDateOrOneMinute(String startDate, String endDate) {
        return StringUtils.isEmpty(endDate) ? parseDateFromString(startDate).plusMinutes(1) : parseDateFromString(endDate);
    }

    public static void validateCreationDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate.isBefore(startDate)) {
            throw new InvalidDateException(String.format("The end Date(%s) can't happen before the start Date(%s).", startDate, endDate));
        }
        if (startDate.isBefore(LocalDateTime.now(ZoneId.systemDefault()))) {
            throw new InvalidDateException(String.format("The start Date(%s) can't happen before now.", startDate));
        }
    }
}
