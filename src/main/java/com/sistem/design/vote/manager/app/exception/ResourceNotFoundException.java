package com.sistem.design.vote.manager.app.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String type, Long id) {
        super(String.format("%s not found with id %s", type, id));
    }
}