package com.sistem.design.vote.manager.app.dao;

import com.sistem.design.vote.manager.app.model.VoteSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteScheduleDAO extends JpaRepository<VoteSchedule, Long> {
}
