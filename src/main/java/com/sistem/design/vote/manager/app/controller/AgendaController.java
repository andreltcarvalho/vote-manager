package com.sistem.design.vote.manager.app.controller;

import com.sistem.design.vote.manager.app.dto.AgendaInsertDTO;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.services.AgendaService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired
    AgendaService agendaService;

    @PostMapping("/create")
    public ResponseEntity<?> createAgenda(@Valid @RequestBody AgendaInsertDTO agenda) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.create(agenda));
    }

    @GetMapping("/read/{agendaId}")
    public ResponseEntity<?> readAgenda(@NonNull @PathVariable Long agendaId) {
        return ResponseEntity.ok().body(agendaService.findById(agendaId));
    }

    @GetMapping("/read")
    public ResponseEntity<?> readAllAgendas() {
        return ResponseEntity.ok().body(agendaService.readAll());
    }

    @PatchMapping("/update/{agendaId}")
    public ResponseEntity<?> updateAgenda(@NonNull @PathVariable Long agendaId, @NonNull @RequestBody Agenda agenda) {
        return ResponseEntity.ok().body(agendaService.updateVoteAgenda(agenda, agendaId));
    }

    @DeleteMapping("/delete/{agendaId}")
    public ResponseEntity<?> deleteAgenda(@PathVariable Long agendaId) {
        agendaService.delete(agendaId);
        return ResponseEntity.noContent().build();
    }
}
