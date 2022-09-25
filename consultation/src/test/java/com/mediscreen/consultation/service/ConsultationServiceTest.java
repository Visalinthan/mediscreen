package com.mediscreen.consultation.service;

import com.mediscreen.consultation.model.Notes;
import com.mediscreen.consultation.repository.NotesRepository;
import com.mediscreen.consultation.vo.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultationServiceTest {

    private NotesRepository notesRepository = Mockito.mock(NotesRepository.class);

    RestTemplate restTemplate  = Mockito.mock(RestTemplate.class);

    private ConsultationService consultationService = new ConsultationService(notesRepository);

    @BeforeEach
    void init(){
        consultationService.setRestTemplate(restTemplate);
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
    void listNotesTest() {
        List<Notes> notes = new ArrayList<>();
        notes.add(getNote());
        when(notesRepository.findAll()).thenReturn(notes);
        assertThat(consultationService.list().get(0).getId()).isEqualTo(getNote().getId());
    }

    @Test
    void listNotesByPatientIdTest() {

        List<Notes> notes = new ArrayList<>();
        notes.add(getNote());

        Patient patient = new Patient(1L,"vishal","john","M", LocalDate.parse("1984-03-06"),"2 rue olive",131404644);

        Mockito.when(restTemplate.getForObject(
                        anyString(),
                        any(Class.class)
                ))
                .thenReturn(patient);

        when(notesRepository.findByPatientId(any())).thenReturn(notes);

        assertThat(consultationService.listNotesByPatientId("1").get(0).getId()).isEqualTo(getNote().getId());
    }

    @Test
    void findNoteByIdTest() {

        Notes notes = getNote();

        when(notesRepository.findById(notes.getId())).thenReturn(Optional.of(getNote()));

        assertThat(consultationService.findById(notes.getId()).get()).isEqualTo(getNote());
    }

    @Test
    void saveNoteTest() {

        Notes notes = getNote();

        Patient patient = new Patient(1L,"vishal","john","M", LocalDate.parse("1984-03-06"),"2 rue olive",131404644);
        Mockito.when(restTemplate.getForObject(
                        anyString(),
                        any(Class.class)
                ))
                .thenReturn(patient);

        when(notesRepository.save((Notes) any())).thenReturn(notes);

        assertThat(consultationService.save(notes,"1")).isEqualTo(notes);
    }

    @Test
    void updateNoteTest() {
        Notes notes = getNote();

        when(notesRepository.findById(notes.getId())).thenReturn(Optional.of(getNote()));
        when(notesRepository.save(ArgumentMatchers.any(Notes.class))).thenReturn(notes);

        assertThat(consultationService.update(notes, notes.getId())).isEqualTo(notes);
    }

    @Test
    void deleteNoteByIdTest() {
        Notes notes = getNote();

        consultationService.deleteById(notes.getId());

        verify(notesRepository, times(1)).deleteById(notes.getId());
    }
}