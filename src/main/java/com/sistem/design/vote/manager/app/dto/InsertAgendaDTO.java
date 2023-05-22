package com.sistem.design.vote.manager.app.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InsertAgendaDTO {
    private String startDate;
    private String endDate;
    private String title;
    private String description;
}
