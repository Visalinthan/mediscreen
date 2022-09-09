package com.mediscreen.consultation.repository;

import com.mediscreen.consultation.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends MongoRepository<Notes, String> {

    @Query("{ 'patientId' : ?0 }")
     List<Notes> findByPatientId(String PatientId);
}
