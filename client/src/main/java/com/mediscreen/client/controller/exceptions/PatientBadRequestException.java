package com.mediscreen.client.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PatientBadRequestException extends Exception {
    public PatientBadRequestException(String requête_incorrecte_) {
        super(requête_incorrecte_);
    }
}
