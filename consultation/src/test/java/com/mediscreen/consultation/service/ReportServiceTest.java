package com.mediscreen.consultation.service;

import com.mediscreen.consultation.model.Notes;
import com.mediscreen.consultation.proxies.MicroservicePatientProxy;
import com.mediscreen.consultation.repository.NotesRepository;
import com.mediscreen.consultation.vo.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private NotesRepository notesRepository;

    @Mock
    private ConsultationService consultationService;

    @Mock
    MicroservicePatientProxy patientProxy;

    @InjectMocks
    private ReportService reportService;

    private static Notes getNote(){

        Notes notes = new Notes();

        notes.setId("100");
        notes.setTitle("Test");
        notes.setDate("2022-05-05");
        notes.setContent("Note de test");
        notes.setPatientId("1");

        return notes;
    }

    @Test
    void getReportPatient() {

        List<Notes> notes = new ArrayList<>();
        notes.add(getNote());

       Patient patient = new Patient(1L,"vishal","john","M", LocalDate.parse("1984-03-06"),"2 rue olive",131404644);

       int age = 34;

        when(patientProxy.getPatientbyId(anyLong())).thenReturn(patient);

        when(patientProxy.getAgePatient(anyLong())).thenReturn(age);

        //when(notesRepository.findByPatientId(any())).thenReturn(notes);

        assertThat(reportService.getReportPatient(1L)).isNotNull();
    }
}