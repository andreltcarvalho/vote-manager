package com.sistem.design.vote.manager.app.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
@Entity
@Accessors(chain = true)
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Value cannot be blank")
    @Pattern(regexp = "^(SIM|NAO)$", message = "Value must be 'SIM' or 'NAO'")
    private String voteResult;

    @NotBlank
    @Size(min = 11, max = 11)
    private String userCpf;

    @Nullable
    private ZonedDateTime voteTime;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    @NotBlank
    private Agenda agendaId;
}
