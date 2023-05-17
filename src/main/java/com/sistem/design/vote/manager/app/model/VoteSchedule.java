package com.sistem.design.vote.manager.app.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
public class VoteSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voteSchedule")
    private List<Vote> votes;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
}
