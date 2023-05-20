package com.sistem.design.vote.manager.app.mapper;


import com.sistem.design.vote.manager.app.dto.AgendaResultDTO;
import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.utils.BusinessUtils;
import com.sistem.design.vote.manager.app.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Agenda mapper.
 */
public class AgendaMapper {
    private AgendaMapper() {
    }

    /**
     * Recebe um DTO de entrada e retorna uma Pauta já criada e válida
     *
     * @param agendaDTO DTO de entrada da Pauta.
     * @return Pauta criada e válida.
     * @throws com.sistem.design.vote.manager.app.exception.InvalidDateException Explicado no método validateCreationDates.
     */
    public static Agenda getFromInsertDTO(InsertAgendaDTO agendaDTO) {
        LocalDateTime startDate = DateUtils.parseDateFromString(agendaDTO.getStartDate());
        LocalDateTime endDate = DateUtils.setEndDateOrOneMinute(agendaDTO.getStartDate(), agendaDTO.getEndDate());
        DateUtils.validateCreationDates(startDate, endDate);
        return new Agenda()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setVotes(new ArrayList<>());
    }

    /**
     * Recebe uma pauta válida e retorna um DTO de leitura.
     *
     * @param agenda Pauta válida
     * @return DTO de leitura com novos atributos
     */
    public static AgendaResultDTO toAgendaResultDTO(Agenda agenda) {
        Map<String, Long> voteCountMap =
                agenda.getVotes()
                        .stream()
                        .collect(Collectors.groupingBy(Vote::getVoteResult, Collectors.counting()));

        long simCount = voteCountMap.getOrDefault("Sim", 0L);
        long naoCount = voteCountMap.getOrDefault("Não", 0L);

        String result = Stream.of(simCount, naoCount)
                .max(Long::compareTo)
                .map(maxCount -> maxCount == simCount ? "APPROVED" : "NOT APPROVED")
                .orElse("TIE");
        return new AgendaResultDTO()
                .setResult(result)
                .setStatus(BusinessUtils.isSessionOpen(agenda) ? "OPEN" : "CLOSED")
                .setAgenda(agenda);
    }
}
