package com.sistem.design.vote.manager.app.exception;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class StandardErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDateTime timestamp;
    private String title;
    private String message;

    public StandardErrorMessage(String title, String message) {
        super();
        this.timestamp = LocalDateTime.now(ZoneId.systemDefault());
        this.title = title;
        this.message = message;
    }
}