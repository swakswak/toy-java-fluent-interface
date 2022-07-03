package com.example.api.exceptions;

import com.example.api.exceptions.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {
    private final Logger log;

    public ApiExceptionHandler() {
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus
    public ExceptionResponse handleNoSuchElementException(NoSuchElementException exception) {
        final String message = exception.getMessage();
        log.error("[handleNoSuchElementException] {}", message);

        return ExceptionResponse.of(HttpStatus.NOT_FOUND, message);
    }
}
