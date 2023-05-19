package com.thompete.conferenceservice.error.exception;

public class UserConflictException extends RuntimeException {
    public UserConflictException(String message) {
        super(message);
    }

    public UserConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserConflictException(Throwable cause) {
        super(cause);
    }
}
