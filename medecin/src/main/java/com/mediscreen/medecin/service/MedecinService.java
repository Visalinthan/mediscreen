package com.mediscreen.medecin.service;

import com.mediscreen.medecin.repository.MedecinRepository;
import org.springframework.stereotype.Service;

@Service
public class MedecinService {
    private MedecinRepository medecinRepository;

    public MedecinService(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }
}
