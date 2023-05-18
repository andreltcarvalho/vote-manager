package com.sistem.design.vote.manager.app.service;

import com.sistem.design.vote.manager.app.dao.VoteDAO;
import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.exception.DatabaseException;
import com.sistem.design.vote.manager.app.mapper.VoteMapper;
import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.model.VoteId;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @SneakyThrows
    public Object insertVote(Long agendaId, @NonNull VoteDTO voteDto) {
        logger.info("Inserting new Vote in the Agenda with ID: {}.", agendaId);
        validateIfUserAlreadyVoted(agendaId, voteDto.getCpf());
        Vote vote = VoteMapper.getVoteFromInsertDTO(voteDto).setAgenda(agendaService.findById(agendaId));
        return voteDAO.save(vote);
    }

    private void validateIfUserAlreadyVoted(Long agendaId, String cpf) {
        VoteId voteId = new VoteId().setAgenda(agendaId).setUserCpf(cpf);
        if (voteDAO.existsById(voteId)) {
            throw new DatabaseException(String.format("The user with cpf '%s' has already voted on the Agenda with id '%s'", cpf, agendaId));
        }
    }

    public List<Vote> readVotesByAgendaId(Long agendaId) {
        logger.info("Reading all Votes from the Agenda with ID: {}", agendaId);
        return voteDAO.findAllByAgendaId(agendaId);
    }
}
