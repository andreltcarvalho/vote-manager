package com.sistem.design.vote.manager.app.mapper;


import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AgendaMapper {
    private AgendaMapper() {
    }

    public static Agenda getFromInsertDTO(InsertAgendaDTO dto) {
        LocalDateTime startDate = DateUtils.parseDateFromString(dto.getStartDate());
        LocalDateTime endDate = DateUtils.setEndDateOrOneMinute(dto.getStartDate(), dto.getEndDate());
        DateUtils.validateCreationDates(startDate, endDate);
        return new Agenda()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setVotes(new ArrayList<>());
    }
}
