package com.sistem.design.vote.manager.app.dao;

import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface VoteDAO extends JpaRepository<Vote, VoteId> {
    @Query("SELECT v FROM Vote v JOIN FETCH v.agenda a WHERE v.id IN :voteIds")
    List<Vote> findVotesWithAgenda(@Param("voteIds") List<VoteId> voteIds);


}
