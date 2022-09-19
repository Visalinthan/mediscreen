package com.mediscreen.consultation.controller;

import com.mediscreen.consultation.controller.exceptions.ImpossibleAjouterNote;
import com.mediscreen.consultation.model.Notes;
import com.mediscreen.consultation.service.ConsultationService;
import com.mediscreen.consultation.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    ConsultationService consultationService;

    @Autowired
    ReportService reportService;

    @GetMapping("/listNote")
    public List<Notes> listOfAllNote() {
        return consultationService.list();
    }


    @GetMapping("/getNote/{id}")
    public Optional<Notes> getNotesById(@PathVariable String id) {
        Optional<Notes> notes = consultationService.findById(id);
        return notes;
    }

    @RequestMapping(value ="/notesByPatient/{id}", method = RequestMethod.GET)
    public List<Notes> listByPatient(@PathVariable("id") String id) {
        return consultationService.listNotesByPatientId(id);
    }

    @RequestMapping(value ="/addNote/{id}", method = RequestMethod.POST)
    public ResponseEntity<Notes> addNote(@PathVariable("id") String id, @RequestBody Notes notes) {

        Notes newNotes = consultationService.save(notes, id);

        if(newNotes == null) throw new ImpossibleAjouterNote("Impossible d'ajouter une note");

        return new ResponseEntity<Notes>(notes, HttpStatus.CREATED);
    }

    @PutMapping("/updateNotes/{id}")
    public ResponseEntity<Notes> updateNotes(@PathVariable("id") String id,@RequestBody Notes notes) {

        consultationService.update(notes, id);

        return new ResponseEntity<Notes>(notes, HttpStatus.OK);
    }

    @DeleteMapping("/deleteNotes/{id}")
    public void deleteNotes(@PathVariable String id) {
        Optional<Notes> notes = consultationService.findById(id);
        consultationService.deleteById(id);

    }

    @RequestMapping(value ="/reportPatient/{id}", method = RequestMethod.GET)
    public String reportPatient(@PathVariable("id") String id) {
        return reportService.getReportPatient(id);
    }



}
