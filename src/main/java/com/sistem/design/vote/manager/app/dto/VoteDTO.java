package com.sistem.design.vote.manager.app.dto;

import lombok.Data;

@Data
public class VoteDTO {
    private String cpf;
    private String voteResult;
    private Long agendaId;
}
