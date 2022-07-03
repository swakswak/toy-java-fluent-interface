package com.example.api.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private final int status;
    private final String error;
    private final String message;
    private final LocalDateTime timestamp;

    private ExceptionResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static ExceptionResponse of(HttpStatus status, String message) {
        return new ExceptionResponse(status, message);
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
