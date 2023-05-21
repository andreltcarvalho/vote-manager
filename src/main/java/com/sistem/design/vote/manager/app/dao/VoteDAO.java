package com.sistem.design.vote.manager.app.dao;

import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteDAO extends JpaRepository<Vote, VoteId> {
}
