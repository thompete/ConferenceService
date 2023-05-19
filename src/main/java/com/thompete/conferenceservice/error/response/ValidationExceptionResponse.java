package com.thompete.conferenceservice.error.response;

import com.thompete.conferenceservice.error.response.ExceptionResponse;

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
