package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Recherche une liste d'offre avec la méthode findAll de l'interface patientRepository
     * @return une liste de patient
     */
    public List<Patient> list(){return this.patientRepository.findAll();}

    /**
     * Recherche une offre en fonction de l'id dans le paramètre avec la méthode findAll de patientRepository
     * @param id
     * @return patient
     */
    public Optional<Patient> findById(Long id){return this.patientRepository.findById(id);}

    /**
     * Sauvergarde d'une offre avec la méthode save de patientRepository
     * @param patient
     * @return une sauvegarde patient
     */
    public Patient save(Patient patient){
        return this.patientRepository.save(patient);
    }

    /**
     * Modification d'un patient en récupérant les éléménts dans les paramètre avec la méthode save de patientRepository
     * @param newPatient
     * @param id
     * @return un patient modifié
     */
    public Patient update(Patient newPatient, Long id){
        Optional<Patient> patientFind = this.patientRepository.findById(id);

        Patient patientUpdated = null;

        if(patientFind.isPresent()){
            Patient patientUpdate = patientFind.get();
            patientUpdate.setFirstName(newPatient.getFirstName());
            patientUpdate.setLastName(newPatient.getLastName());
            patientUpdate.setSex(newPatient.getSex());
            patientUpdate.setDob(newPatient.getDob());
            patientUpdate.setAddress(newPatient.getAddress());
            patientUpdate.setPhone(newPatient.getPhone());

            patientUpdated = patientRepository.save(patientUpdate);
        }

        return patientUpdated;
    }

    /**
     * Suppression d'une offre en récupérant l'id dans le paramètre avec la méthode deleteById de bidListRepository
     * @param id
     */
    public void deleteById(Long id){
        this.patientRepository.deleteById(id);
    }
}
