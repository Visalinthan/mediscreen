package com.mediscreen.consultation.service;

import com.mediscreen.consultation.model.Notes;
import com.mediscreen.consultation.repository.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {
    private NotesRepository notesRepository;

    public ConsultationService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<Notes> list(){return this.notesRepository.findAll();}

    public List<Notes> listByPatient(String id){return this.notesRepository.findByPatientId(id);}

    public Optional<Notes> findById(String id){return this.notesRepository.findById(id);}


    public Notes save(Notes note){
        return this.notesRepository.save(note);
    }

}
