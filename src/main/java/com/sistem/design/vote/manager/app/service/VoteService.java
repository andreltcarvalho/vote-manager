package com.sistem.design.vote.manager.app.service;

import com.sistem.design.vote.manager.app.adapter.CpfValidatorAdapter;
import com.sistem.design.vote.manager.app.dao.VoteDAO;
import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.mapper.VoteMapper;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.model.VoteId;
import com.sistem.design.vote.manager.app.utils.BusinessUtils;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Agenda service.
 */
@Service
@Slf4j
public class VoteService {

    Logger logger = LoggerFactory.getLogger(VoteService.class);
    @Autowired
    AgendaService agendaService;

    @Autowired
    VoteDAO voteDAO;

    @Autowired
    CpfValidatorAdapter cpfValidatorAdapter;

    @SneakyThrows
    public Object insertVote(Long agendaId, @NonNull VoteDTO voteDto) {
        logger.info("Inserting new Vote in the Agenda with ID: {}.", agendaId);
        cpfValidatorAdapter.validateCpf(voteDto.getCpf());
        validateIfUserAlreadyVoted(agendaId, voteDto.getCpf());
        Vote vote = VoteMapper.getVoteFromInsertDTO(voteDto).setAgenda(agendaService.findById(agendaId));
        BusinessUtils.validateIfTheSessionIsOpen(vote);
        return voteDAO.save(vote);
    }

    private void validateIfUserAlreadyVoted(Long agendaId, String cpf) {
        VoteId voteId = new VoteId().setAgenda(agendaId).setUserCpf(cpf);
        if (voteDAO.existsById(voteId)) {
            throw new BusinessException("This user has already voted on the selected Agenda");
        }
    }
}
