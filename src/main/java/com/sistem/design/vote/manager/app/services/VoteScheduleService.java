package com.sistem.design.vote.manager.app.services;

import com.sistem.design.vote.manager.app.adapter.CpfValidatorAdapter;
import com.sistem.design.vote.manager.app.dao.VoteScheduleDAO;
import com.sistem.design.vote.manager.app.model.VoteSchedule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Vote schedule service.
 */
@Service
@Slf4j
public class VoteScheduleService {

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(VoteScheduleService.class);

    /**
     * The Cpf validator adapter.
     */
    @Autowired
    CpfValidatorAdapter cpfValidatorAdapter;

    /**
     * The Vote schedule dao.
     */
    @Autowired
    VoteScheduleDAO voteScheduleDAO;

    /**
     * Create vote schedule.
     *
     * @param voteSchedule the vote schedule
     * @return the vote schedule
     */
    public VoteSchedule create(VoteSchedule voteSchedule) {
        logger.info("creating vote Schedule: {}", voteSchedule);
        voteSchedule.setVotes(new ArrayList<>());
        return voteScheduleDAO.save(voteSchedule);
    }

    /**
     * Read all list.
     *
     * @return Todas as Pautas cadastradas
     */
    public List<VoteSchedule> readAll() {
        logger.info("reading all Vote Schedules.");
        return voteScheduleDAO.findAll();
    }

    /**
     * Find by id vote schedule.
     *
     * @param scheduleId Id interno da Pauta
     * @return Pauta com o id inserito
     */
    @SneakyThrows
    public VoteSchedule findById(Long scheduleId) {
        logger.info("Reading Vote Schedule with ID: {}.", scheduleId);
        return voteScheduleDAO.findById(scheduleId).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    /**
     * Update vote schedule object.
     *
     * @param voteSchedule entidade de Pauta com os atributos à serem atualizados
     * @param scheduleId   id Interno da Pauta
     * @return Pauta atualziada
     */
    @SneakyThrows

    public Object updateVoteSchedule(VoteSchedule voteSchedule, Long scheduleId) {
        VoteSchedule databaseVoteSchedule = voteScheduleDAO.findById(scheduleId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        //TODO: criar logica de update
        return voteScheduleDAO.save(databaseVoteSchedule);
    }
}
