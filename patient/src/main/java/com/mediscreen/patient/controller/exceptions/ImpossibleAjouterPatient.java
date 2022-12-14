package com.mediscreen.patient.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImpossibleAjouterPatient extends RuntimeException {
    public ImpossibleAjouterPatient(String message) {
        super(message);
    }
}
