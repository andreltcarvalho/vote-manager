package com.sistem.design.vote.manager.app.controller;

import com.sistem.design.vote.manager.app.model.Vote;
import com.sistem.design.vote.manager.app.model.VoteSchedule;
import com.sistem.design.vote.manager.app.services.VoteScheduleService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/schedule")
public class VoteScheduleController {

    @Autowired
    VoteScheduleService voteScheduleService;


    @PostMapping("/create")
    public ResponseEntity<?> post(@NonNull @RequestBody VoteSchedule voteSchedule) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(voteScheduleService.create(voteSchedule));
    }

    @GetMapping("/read/{scheduleId}")
    public ResponseEntity<?> read(@NonNull @PathVariable Long scheduleId) {
        return ResponseEntity.ok().body(voteScheduleService.findById(scheduleId));
    }

    @GetMapping("/read")
    public ResponseEntity<?> readAll() {
        return ResponseEntity.ok().body(voteScheduleService.readAll());
    }

    @PatchMapping("/update/{scheduleId}")
    public ResponseEntity<?> update(@NonNull @PathVariable Long scheduleId, @NonNull @RequestBody VoteSchedule voteSchedule) {
        return ResponseEntity.ok().body(voteScheduleService.updateVoteSchedule(voteSchedule, scheduleId));
    }
}
