package com.thompete.conferenceservice.exception.response;

import java.util.Map;

public class ValidationExceptionResponse extends ExceptionResponse {
    Map<String, String> errors;

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
