package com.mediscreen.medecin.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MedecinIntrouvableException extends RuntimeException {
    public MedecinIntrouvableException(String s) {
        super(s);
    }
}
