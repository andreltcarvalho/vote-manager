package com.sistem.design.vote.manager.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@Accessors(chain = true)
public class VoteId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userCpf;
    @JsonIgnore
    private Long agenda;
}
