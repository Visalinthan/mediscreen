package com.mediscreen.medecin.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImpossibleAjouterMedecin extends RuntimeException {
    public ImpossibleAjouterMedecin(String s) {
        super(s);
    }
}
