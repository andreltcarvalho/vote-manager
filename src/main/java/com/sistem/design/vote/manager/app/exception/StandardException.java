package com.sistem.design.vote.manager.app.exception;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class StandardException extends Throwable implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private StandardException() {

    }

    public StandardException(Integer status, String error, String message) {
        super();
        this.timestamp = LocalDateTime.now(ZoneId.systemDefault());
        this.status = status;
        this.error = error;
        this.message = message;
    }
}