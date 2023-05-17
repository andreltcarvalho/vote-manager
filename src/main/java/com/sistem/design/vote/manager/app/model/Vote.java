package com.sistem.design.vote.manager.app.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Accessors(chain = true)
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String voteResult;
    private String cpf;
    private ZonedDateTime voteTime;
    @ManyToOne
    @JoinColumn(name = "vote_schedule_id")
    private VoteSchedule voteSchedule;
}
