package com.sistem.design.vote.manager.app.exception;

public class InvalidDateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidDateException(String message) {
        super(message);
    }
}

