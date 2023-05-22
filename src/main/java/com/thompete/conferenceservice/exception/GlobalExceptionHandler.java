package com.thompete.conferenceservice.exception;

import com.thompete.conferenceservice.exception.type.ConflictException;
import com.thompete.conferenceservice.exception.type.NotFoundException;
import com.thompete.conferenceservice.exception.response.ValidationExceptionResponse;
import com.thompete.conferenceservice.exception.response.ExceptionResponse;
import com.thompete.conferenceservice.exception.type.UnauthorizedException;
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
    public ResponseEntity<ExceptionResponse> handleConflictException(ConflictException e) {
        return handleException(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleUnauthorizedException(UnauthorizedException e) {
        return handleException(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleOtherExceptions(Exception e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
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

    public ResponseEntity<ExceptionResponse> handleException(Exception e, HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse();
        response.setTimestamp(Instant.now());
        response.setStatus(status.value());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, status);
    }
}
