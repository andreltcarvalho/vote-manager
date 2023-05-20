package com.sistem.design.vote.manager.builder;

import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.mapper.AgendaMapper;
import com.sistem.design.vote.manager.app.mapper.VoteMapper;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.model.Vote;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * The type Test entity builder.
 */
public class AgendaTestEntityBuilder {
    private AgendaTestEntityBuilder() {

    }

    /**
     * Formatador para manter as datas no padrão esperado
     */
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * Passando somente a data inicial o sistema considera um default de 1 minuto para a data final.
     *
     * @param startDate Data de abertura da Pauta
     * @return Pauta
     */
    public static Agenda buildTestAgendaWithStartDate(LocalDateTime startDate) {
        return buildAgenda(startDate.format(formatter), "");
    }

    /**
     * @param startDate Data de abertura da pauta
     * @param endDate   Data de fechamento da pauta
     * @return Pauta
     * @throws com.sistem.design.vote.manager.app.exception.InvalidDateException Caso a data final seja antes da data inicial.
     */
    public static Agenda buildTestAgendaWithDates(LocalDateTime startDate, LocalDateTime endDate) {
        return buildAgenda(startDate.format(formatter), endDate.format(formatter));
    }

    private static Agenda buildAgenda(String startDate, String endDate) {
        return AgendaMapper.getFromInsertDTO(new InsertAgendaDTO().setStartDate(startDate).setEndDate(endDate)).setId(1L);
    }

    /**
     * Abstração para pegar o horário atual nos testes
     *
     * @return Hora atual
     */
    public static LocalDateTime getNow() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }
}
