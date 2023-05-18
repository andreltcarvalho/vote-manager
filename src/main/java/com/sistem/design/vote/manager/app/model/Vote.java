package com.sistem.design.vote.manager.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "votes")
@IdClass(VoteId.class)
@Accessors(chain = true)
public class Vote {

    @NotBlank(message = "Value cannot be blank")
    @Pattern(regexp = "^(Sim|Não)$", message = "The values must be 'Sim' or 'Não'")
    private String voteResult;

    @NotNull
    @Id
    @Size(min = 11, max = 11)
    @Column(name = "cpf", unique = true)
    private String userCpf;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime voteTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id", referencedColumnName = "id")
    @JsonIgnore
    @NotNull
    @Id
    private Agenda agenda;

    public VoteId getId() {
        VoteId id = new VoteId();
        id.setAgenda(agenda.getId());
        id.setUserCpf(userCpf);
        return id;
    }

}
