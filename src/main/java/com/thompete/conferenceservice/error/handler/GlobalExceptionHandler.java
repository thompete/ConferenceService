package com.thompete.conferenceservice.error.handler;

import com.thompete.conferenceservice.error.exception.UserConflictException;
import com.thompete.conferenceservice.error.exception.UserNotFoundException;
import com.thompete.conferenceservice.error.response.ValidationExceptionResponse;
import com.thompete.conferenceservice.error.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleCreateUserException(UserConflictException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setTimestamp(Instant.now());
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setTimestamp(Instant.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException e) {
        ValidationExceptionResponse response = new ValidationExceptionResponse();
        response.setTimestamp(Instant.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Błąd walidacji");
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.setErrors(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
