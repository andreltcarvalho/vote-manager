package com.sistem.design.vote.manager.app.dao;

import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteDAO extends JpaRepository<Vote, VoteId> {
    @Query("SELECT v FROM Vote v WHERE v.agenda.id = :agendaId")
    List<Vote> findAllByAgendaId(@Param("agendaId") Long agendaId);
}
