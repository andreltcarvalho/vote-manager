package com.sistem.design.vote.manager.app.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VoteDTO {
    private String cpf;
    private String voteResult;
    private Long agendaId;
}
