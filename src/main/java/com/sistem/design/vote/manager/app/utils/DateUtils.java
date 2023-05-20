package com.sistem.design.vote.manager.app.utils;

import com.sistem.design.vote.manager.app.exception.InvalidDateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * The type Date utils.
 */
public class DateUtils {
    private DateUtils() {
    }
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * Parse date from string local date time.
     *
     * @param date Data em String
     * @return Horário no tipo LocalDateTime
     */
    public static LocalDateTime parseDateFromString(String date) {
        Assert.notNull(date,"Date can't be empty");
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Recebe duas datas, caso a data final esteja vazia, será considerada como 1 minuto após a data inicial.
     *
     * @param startDate data inicial
     * @param endDate  data final
     * @return A data final ou um minuto após a data inicial
     */
    public static LocalDateTime setEndDateOrOneMinute(String startDate, String endDate) {
        return StringUtils.isEmpty(endDate) ? parseDateFromString(startDate).plusMinutes(1) : parseDateFromString(endDate);
    }

    /**
     * Valida se as datas estão válidas.
     *
     * @param startDate Data inicial
     * @param endDate   Data final
     * @throws InvalidDateException Caso a data final esteja antes da data inicial ou caso a data inicial seja no passado.
     */
    public static void validateCreationDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate.isBefore(startDate)) {
            throw new InvalidDateException(String.format("The end Date(%s) can't happen before the start Date(%s).", startDate, endDate));
        }
        if (startDate.isBefore(LocalDateTime.now(ZoneId.systemDefault()))) {
            throw new InvalidDateException(String.format("The start Date(%s) can't happen before now.", startDate));
        }
    }
}
