package com.mediscreen.medecin.controller;

import com.mediscreen.medecin.controller.exceptions.ImpossibleAjouterMedecin;
import com.mediscreen.medecin.controller.exceptions.MedecinIntrouvableException;
import com.mediscreen.medecin.model.Medecin;
import com.mediscreen.medecin.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medecin")
public class MedecinController {

    @Autowired
    MedecinService medecinService;

    @GetMapping("/listMedecin")
    public List<Medecin> listOfAllMedecin() {
        return medecinService.list();
    }

    @GetMapping("/getMedecin/{id}")
    public Medecin getMedecinById(@PathVariable Long id) {
        Optional<Medecin> patient = medecinService.findById(id);
        return patient.orElseThrow(()-> new MedecinIntrouvableException("Le medecin avec l'id " + id + " est INTROUVABLE. "));
    }

    @PostMapping("/addMedecin")
    public ResponseEntity<Medecin> addPatient(@RequestBody Medecin medecin) {

        Medecin newMedecin = medecinService.save(medecin);

        if(newMedecin == null) throw new ImpossibleAjouterMedecin("Impossible d'ajouter le medecin");

        return new ResponseEntity<Medecin>(medecin, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteMedecin/{id}")
    public ResponseEntity<Long> deleteBid(@PathVariable Long id) {
        Optional<Medecin> medecin = medecinService.findById(id);
        if(medecin==null) throw new MedecinIntrouvableException("Le medecin avec l'id " + id + " est INTROUVABLE. ");
        medecinService.deleteById(id);

        return ResponseEntity.ok(id);
    }
}
