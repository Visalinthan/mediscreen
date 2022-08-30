package com.mediscreen.patient.controller;

import com.mediscreen.patient.controller.exceptions.ImpossibleAjouterPatient;
import com.mediscreen.patient.controller.exceptions.PatientIntrouvableException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;


    @GetMapping("/listPatient")
    public List<Patient> listOfAllPatient() {
        return patientService.list();
    }

    @GetMapping("/getPatient/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.findById(id);
        return patient.orElseThrow(()-> new PatientIntrouvableException("Le patient avec l'id " + id + " est INTROUVABLE. "));
    }

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {

        Patient newPatient = patientService.save(patient);

        if(newPatient == null) throw new ImpossibleAjouterPatient("Impossible d'ajouter le patient");

        return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id,@RequestBody Patient patient) {
        if(patient==null) throw new PatientIntrouvableException("Le patient avec l'id " + id + " est INTROUVABLE. ");

        Patient patientUpdate = patientService.update(patient, id);

        if(patientUpdate == null) throw new ImpossibleAjouterPatient("Impossible de mettre à jour le patient");

        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<Long> deleteBid(@PathVariable Long id) {
        Optional<Patient> patient = patientService.findById(id);
        if(patient==null) throw new PatientIntrouvableException("Le patient avec l'id " + id + " est INTROUVABLE. ");
        patientService.deleteById(id);

        return ResponseEntity.ok(id);
    }


}
