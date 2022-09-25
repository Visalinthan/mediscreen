package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private static Patient getPatient(){
        LocalDate dob = LocalDate.parse("1999-01-08");

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Jean");
        patient.setLastName("Luc");
        patient.setPhone(145632598);
        patient.setAddress("10 rue paris 75003");
        patient.setSex("M");
        patient.setDob(dob);

        return patient;
    }


    @Test
    public void listPatientTest(){
        List<Patient> patients = new ArrayList<>();
        patients.add(getPatient());
        when(patientRepository.findAll()).thenReturn(patients);
        assertThat(patientService.list().get(0).getId()).isEqualTo(getPatient().getId());
    }

    @Test
    public void findPatientByIdTest() {
        Patient patient = getPatient();

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(getPatient()));

        assertThat(patientService.findById(patient.getId()).get()).isEqualTo(getPatient());
    }

    @Test
    public void savePatientTest() {
        Patient patient = getPatient();

        when(patientRepository.save(ArgumentMatchers.any(Patient.class))).thenReturn(patient);

        assertThat(patientService.save(patient)).isEqualTo(patient);
    }

    @Test
    public void updatePatientTest() {
        Patient patient = getPatient();

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(getPatient()));
        when(patientRepository.save(ArgumentMatchers.any(Patient.class))).thenReturn(patient);

        assertThat(patientService.update(patient, patient.getId())).isEqualTo(patient);
    }

    @Test
    public void deletePatientByIdTest(){
        Patient patient = getPatient();

        patientService.deleteById(patient.getId());

        verify(patientRepository, times(1)).deleteById(patient.getId());
    }

    @Test
    public void calculAgePatientTest(){
        Patient patient = getPatient();

        assertThat(patientService.calculAge(patient)).isEqualTo(patient.calculateAge(getPatient().getDob()));
    }
}