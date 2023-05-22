package com.sistem.design.vote.manager.app.controller;

import com.sistem.design.vote.manager.app.dto.AgendaResultDTO;
import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.mapper.AgendaMapper;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.service.AgendaService;
import com.sistem.design.vote.manager.app.service.VoteService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired
    AgendaService agendaService;

    @Autowired
    VoteService voteService;

    @PostMapping
    public ResponseEntity<?> createAgenda(@Valid @RequestBody InsertAgendaDTO agenda) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.create(agenda));
    }

    @PostMapping("/vote/{agendaId}")
    public ResponseEntity<?> vote(@PathVariable Long agendaId, @NonNull @RequestBody VoteDTO vote) {
        return ResponseEntity.ok().body(voteService.insertVote(agendaId, vote));
    }

    @GetMapping("/{agendaId}")
    public ResponseEntity<AgendaResultDTO> readAgenda(@NonNull @PathVariable Long agendaId) {
        return ResponseEntity.ok().body(AgendaMapper.toAgendaResultDTO(agendaService.findById(agendaId)));
    }

    @GetMapping
    public ResponseEntity<List<Agenda>> readAllAgendas() {
        return ResponseEntity.ok().body(agendaService.readAll());
    }

    @DeleteMapping("/{agendaId}")
    public ResponseEntity<?> deleteAgenda(@PathVariable Long agendaId) {
        agendaService.delete(agendaId);
        return ResponseEntity.noContent().build();
    }
}
