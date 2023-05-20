package com.sistem.design.vote.manager.app.mapper;


import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.utils.BusinessUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * The type Vote mapper.
 */
public class VoteMapper {
    private VoteMapper() {
    }

    /**
     * Recebe um objeto DTO de entrada como parâmetro e retorna um voto criado.
     *
     * @param dto DTO de entrada do Voto
     * @return Entidade do Voto já criada
     * @throws BusinessException Quando o voto for diferente de "Sim" e "Não" ou caso Pauta estiver fechada.
     */
    public static Vote getVoteFromInsertDTO(VoteDTO dto) {
        return new Vote()
                .setVoteTime(LocalDateTime.now(ZoneId.systemDefault()))
                .setUserCpf(dto.getCpf())
                .setVoteResult(BusinessUtils.validateAndReturnVoteResult(dto.getVoteResult()));
    }
}
