package com.sistem.design.vote.manager.app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFound(ResourceNotFoundException e, WebRequest request) {
        return handleException("Resource not found.", e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> resourceNotFound(EmptyResultDataAccessException e, WebRequest request) {
        return handleException("Resource not found.", e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<?> invalidDate(InvalidDateException e, WebRequest request) {
        return handleException("Error while parsing the Date", e, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<?> database(DatabaseException e, WebRequest request) {
        return handleException("Database Error.", e, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> timeParseException(DateTimeParseException e, WebRequest request) {
        return handleException("Error while parsing the Date.", e, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = new StandardErrorMessage(status.getReasonPhrase(), ex.getMessage());
        }
        logger.error("ERROR IN THE APPLICATION: " + ex.getMessage());
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> erros = ex.getBindingResult().getAllErrors();
        List<String> body = new ArrayList<>();
        erros.forEach(objectError -> body.add(objectError.getDefaultMessage()));
        StandardErrorMessage error = new StandardErrorMessage("Erro de Validação", body.toString());
        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    protected ResponseEntity<Object> handleException(String message, Exception e, WebRequest request, HttpStatus notFound) {
        StandardErrorMessage errorMessage = new StandardErrorMessage(message, e.getMessage());
        return handleExceptionInternal(e, errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}