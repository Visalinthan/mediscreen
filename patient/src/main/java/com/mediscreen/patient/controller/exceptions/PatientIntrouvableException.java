package com.mediscreen.patient.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PatientIntrouvableException extends RuntimeException {
    public PatientIntrouvableException(String message) {
        super(message);
    }
}
