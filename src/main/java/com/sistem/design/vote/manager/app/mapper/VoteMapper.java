package com.sistem.design.vote.manager.app.mapper;


import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.utils.BusinessUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class VoteMapper {
    private VoteMapper() {
    }

    public static Vote getVoteFromInsertDTO(VoteDTO dto) {
        return new Vote()
                .setVoteTime(LocalDateTime.now(ZoneId.systemDefault()))
                .setUserCpf(dto.getCpf())
                .setVoteResult(BusinessUtils.validateAndReturnVoteResult(dto.getVoteResult()));
    }
}
