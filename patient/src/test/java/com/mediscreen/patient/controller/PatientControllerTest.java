package com.mediscreen.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.jayway.jsonpath.JsonPath;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    private MockMvc mockMvc;

    @Mock
    PatientService patientService;

    @InjectMocks
    PatientController patientController;

    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(patientController)
                .build();
    }

    @Test
    void listOfAllPatientTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/listPatient")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientByIdTest() throws Exception {

        Patient patient = new Patient( 21L,"vishal","john","M", LocalDate.parse("1984-03-06"),"2 rue olive",131404644);

        when(patientService.findById(anyLong())).thenReturn(java.util.Optional.of(patient));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/getPatient/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addPatientTest() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        ObjectNode node = obj.createObjectNode();
        node.set("firstName", TextNode.valueOf("vishal"));
        node.set("lastName", TextNode.valueOf("john"));
        node.set("sex", TextNode.valueOf("M"));
        node.set("dob", TextNode.valueOf("1984-03-06"));
        node.set("address", TextNode.valueOf("2 rue olive"));
        node.set("phone", TextNode.valueOf("131404644"));

        String jsonPatient = node.toString();


        Patient patient = new Patient( 21L,"vishal","john","M", LocalDate.parse("1984-03-06"),"2 rue olive",131404644);

        when(patientService.save((Patient) any())).thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/patient/addPatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPatient))
                .andExpect(status().isCreated());

    }

    @Test
    void updatePatientTest() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        ObjectNode node = obj.createObjectNode();
        node.set("firstName", TextNode.valueOf("vishal"));
        node.set("lastName", TextNode.valueOf("john"));
        node.set("sex", TextNode.valueOf("M"));
        node.set("dob", TextNode.valueOf("1984-03-06"));
        node.set("address", TextNode.valueOf("2 rue olive"));
        node.set("phone", TextNode.valueOf("131404644"));


        String jsonPatient = node.toString();

        Patient patient = new Patient( 21L,"vishal","john","M", LocalDate.parse("1984-03-06"),"2 rue olive",131404644);

        when(patientService.update((Patient) any(),anyLong())).thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/patient/updatePatient/{id}",21)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPatient))
                .andExpect(status().isOk());
    }

    @Test
    void deletePatientTest() throws Exception {

        Patient patient = new Patient( 21L,"vishal","john","M", LocalDate.parse("1984-03-06"),"2 rue olive",131404644);

        doNothing().when(patientService).deleteById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/patient/deletePatient/{id}",21)
        ).andExpect(status().isOk());
    }

   @Test
    void calculAgeTest() throws Exception {

        Patient patient = new Patient( 21L,"vishal","john","M", LocalDate.parse("1984-03-06"),"2 rue olive",131404644);

        when(patientService.findById(anyLong())).thenReturn(java.util.Optional.of(patient));
        when(patientService.calculAge((Patient) any())).thenReturn(38);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/getAge/{id}",21)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}