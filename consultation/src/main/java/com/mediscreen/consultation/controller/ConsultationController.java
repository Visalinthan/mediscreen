package com.mediscreen.consultation.controller;

import com.mediscreen.consultation.controller.exceptions.ImpossibleAjouterNote;
import com.mediscreen.consultation.model.Notes;
import com.mediscreen.consultation.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    ConsultationService consultationService;

    @GetMapping("/listNote")
    public List<Notes> listOfAllNote() {
        return consultationService.list();
    }


    @RequestMapping(value ="/notesByPatient/{id}", method = RequestMethod.GET)
    public List<Notes> listByPatient(@PathVariable("id") String id) {
        return consultationService.listByPatient(id);
    }

    @PostMapping("/addNote")
    public ResponseEntity<Notes> addNote(@RequestBody Notes notes) {

        Notes newNotes = consultationService.save(notes);

        if(newNotes == null) throw new ImpossibleAjouterNote("Impossible d'ajouter le patient");

        return new ResponseEntity<Notes>(notes, HttpStatus.CREATED);
    }



}
