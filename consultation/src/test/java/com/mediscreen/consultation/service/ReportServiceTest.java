package com.mediscreen.consultation.service;

import com.mediscreen.consultation.model.Notes;
import com.mediscreen.consultation.repository.NotesRepository;
import com.mediscreen.consultation.vo.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    private NotesRepository notesRepository = Mockito.mock(NotesRepository.class);

    private ConsultationService consultationService = Mockito.mock(ConsultationService.class);

    RestTemplate restTemplate  = Mockito.mock(RestTemplate.class);

    private ReportService reportService = new ReportService(notesRepository,consultationService);

    @BeforeEach
    void init(){
        reportService.setRestTemplate(restTemplate);
    }

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

       /*  List<Notes> notes = new ArrayList<>();
        notes.add(getNote());

       Patient patient = new Patient(1L,"vishal","john","M", LocalDate.parse("1984-03-06"),"2 rue olive",131404644);

        int age = 34;

        Mockito.when(restTemplate.getForObject(
                        any(), any(Class.class)
                ))
                .thenReturn(patient);

        Mockito.when(restTemplate.getForObject(
                        any(), eq(int.class)
                ))
                .thenReturn(age);

        when(notesRepository.findByPatientId(any())).thenReturn(notes);

        assertThat(reportService.getReportPatient("19")).isEqualTo(anyString());*/
    }
}