package com.sistem.design.vote.manager.app.service;

import com.sistem.design.vote.manager.app.adapter.CpfValidatorAdapter;
import com.sistem.design.vote.manager.app.dao.AgendaDAO;
import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.exception.DatabaseException;
import com.sistem.design.vote.manager.app.exception.ResourceNotFoundException;
import com.sistem.design.vote.manager.app.mapper.AgendaMapper;
import com.sistem.design.vote.manager.app.model.Agenda;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Agenda service.
 */
@Service
@Slf4j
public class AgendaService {

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(AgendaService.class);

    /**
     * The Cpf validator adapter.
     */
    @Autowired
    CpfValidatorAdapter cpfValidatorAdapter;

    /**
     * The Agenda dao.
     */
    @Autowired
    AgendaDAO agendaDAO;

    /**
     * Create agenda.
     *
     * @param agenda the agenda
     * @return the agenda
     */
    public Agenda create(InsertAgendaDTO agenda) {
        logger.info("creating Agenda: {}", agenda);
        return agendaDAO.save(AgendaMapper.getFromInsertDTO(agenda));
    }

    /**
     * Read all list.
     *
     * @return Todas as Pautas cadastradas
     */
    public List<Agenda> readAll() {
        logger.info("reading all Agendas.");
        return agendaDAO.findAll();
    }

    /**
     * Find by id agenda.
     *
     * @param agendaId Id interno da Pauta
     * @return Pauta com o id inserito
     */
    @SneakyThrows
    public Agenda findById(Long agendaId) {
        logger.info("Reading Agenda with ID: {}.", agendaId);
        return agendaDAO.findById(agendaId).orElseThrow(() -> new ResourceNotFoundException("Agenda", agendaId));
    }

    public void delete(Long agendaId) {
        logger.info("Deleting Agenda with ID: {}.", agendaId);
        try {
            agendaDAO.deleteById(agendaId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Agenda", agendaId);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
