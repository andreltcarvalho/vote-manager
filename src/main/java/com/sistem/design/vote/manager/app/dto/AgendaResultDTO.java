package com.sistem.design.vote.manager.app.dto;

import com.sistem.design.vote.manager.app.model.Agenda;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AgendaResultDTO {
    private String result;
    private String status;
    private Agenda agenda;
}
