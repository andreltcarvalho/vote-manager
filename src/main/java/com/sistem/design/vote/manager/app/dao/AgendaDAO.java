package com.sistem.design.vote.manager.app.dao;

import com.sistem.design.vote.manager.app.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaDAO extends JpaRepository<Agenda, Long> {
}
