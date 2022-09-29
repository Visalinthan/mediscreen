package com.mediscreen.consultation.service;

import com.mediscreen.consultation.model.Notes;
import com.mediscreen.consultation.proxies.MicroservicePatientProxy;
import com.mediscreen.consultation.repository.NotesRepository;
import com.mediscreen.consultation.vo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private NotesRepository notesRepository;

    private final MicroservicePatientProxy patientProxy;


    public ConsultationService(NotesRepository notesRepository, MicroservicePatientProxy patientProxy) {
        this.notesRepository = notesRepository;
        this.patientProxy = patientProxy;
    }

    public List<Notes> list(){return this.notesRepository.findAll();}

    public List<Notes> listNotesByPatientId(Long id){
        Patient patient = patientProxy.getPatientbyId(id);
        String str_PatientId = Long.toString(patient.getId());
        return this.notesRepository.findByPatientId(str_PatientId);
    }

    public Optional<Notes> findById(String id){return this.notesRepository.findById(id);}

    public Notes save(Notes note, Long PatientId){
        Patient patient = patientProxy.getPatientbyId(PatientId);;
        String Str_PatientId = Long.toString(patient.getId());
        note.setPatientId(Str_PatientId);
        return this.notesRepository.save(note);
    }

    public Notes update(Notes newNotes, String id){
        Optional<Notes> noteFind = this.notesRepository.findById(id);

        Notes noteUpdated = null;

        if(noteFind.isPresent()){
            Notes noteUpdate = noteFind.get();
            noteUpdate.setTitle(newNotes.getTitle());
            noteUpdate.setContent(newNotes.getContent());
            noteUpdate.setDate(LocalDate.now().toString());

            noteUpdated = notesRepository.save(noteUpdate);
        }

        return noteUpdated;
    }

    public void deleteById(String id){
        this.notesRepository.deleteById(id);
    }

}
