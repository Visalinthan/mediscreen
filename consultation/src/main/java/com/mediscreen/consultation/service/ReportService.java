package com.mediscreen.consultation.service;

import com.mediscreen.consultation.model.Notes;
import com.mediscreen.consultation.proxies.MicroservicePatientProxy;
import com.mediscreen.consultation.repository.NotesRepository;
import com.mediscreen.consultation.vo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private ConsultationService consultationService;

    private final MicroservicePatientProxy patientProxy;


    public ReportService(NotesRepository notesRepository, ConsultationService consultationService, MicroservicePatientProxy patientProxy) {
        this.notesRepository = notesRepository;
        this.consultationService = consultationService;
        this.patientProxy = patientProxy;
    }

    public String getReportPatient(Long id){

        Patient patient = patientProxy.getPatientbyId(id);

        int age = patientProxy.getAgePatient(id);

        List<Notes> notes = consultationService.listNotesByPatientId(id);

        String terms[] = { "Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps" };

        int count = 0;

        for(Notes n : notes){
            for(String t : terms){
                if(n.getContent().contains(t) || n.getContent().contains(t.toLowerCase())){
                    count = count+1;
                }
            }

        }

        String levelRisk = null;

        if(count <= 1){
            levelRisk= "None";
        }
        if(count >= 2 && age > 30 ){
            levelRisk= "Borderline";
        }
        if(patient.getSex().contentEquals("M" ) && age < 30 && count >= 3 ||
                patient.getSex().contentEquals("F")  && age < 30 && count >= 4 ||
                age > 30 && count >= 6){
            levelRisk= "In Danger";
        }
        if(patient.getSex().contentEquals("M" ) && age < 30 && count >= 5 ||
                patient.getSex().contentEquals("F" ) && age < 30 && count >= 7 ||
                age > 30 && count >= 8){
            levelRisk= "Early onset";
        }

        return levelRisk;
    }
}
