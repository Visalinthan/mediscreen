package com.mediscreen.client.controller;

import com.mediscreen.client.beans.NoteBean;
import com.mediscreen.client.beans.PatientBean;
import com.mediscreen.client.proxies.MicroserviceNoteProxy;
import com.mediscreen.client.proxies.MicroservicePatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {

    private final MicroservicePatientProxy patientProxy;
    private final MicroserviceNoteProxy noteProxy;

    public ClientController(MicroservicePatientProxy patientProxy, MicroserviceNoteProxy noteProxy){
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
    }

    @RequestMapping("/")
    public String listPatient(Model model){
        List<PatientBean> patients =  patientProxy.listPatients();
        model.addAttribute("patients", patients);
        return "patient/list-patient";
    }

    @RequestMapping("patient/get-patient/{id}")
    public String getPatientById(@PathVariable Long id, Model model){
        PatientBean patient = patientProxy.getPatientbyId(id);
        String StrId = Long.toString(id);
        String report = noteProxy.reportPatient(StrId);
        List<NoteBean> notes =  noteProxy.listNoteByPatientId(StrId);
        model.addAttribute("notes", notes);
        model.addAttribute("patient", patient);
        model.addAttribute("report", report);
        return "patient/get-patient";
    }

    @GetMapping("/patient/add-patient")
    public String addPatientForm(PatientBean patient) {
        return "patient/add-patient";
    }

    @PostMapping("/patient/validate")
    public String validatePatient(@Valid PatientBean patientBean, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            patientProxy.addPatient(patientBean);
            List<PatientBean> patients =  patientProxy.listPatients();
            model.addAttribute("patients", patients);
            return "redirect:/";
        }
        return "patient/add-patient";
    }

    @GetMapping("/patient/update-patient/{id}")
    public String updatePatientForm(@PathVariable("id") Long id, Model model) {
        PatientBean patient = patientProxy.getPatientbyId(id);
        model.addAttribute("patient", patient);
        return "patient/update-patient";
    }

    @PostMapping("/patient/update-patient/{id}")
    public String updatePatientById(@PathVariable("id") Long id, @Valid PatientBean patientBean,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "patient/update-patient";
        }
        patientProxy.updatePatient(id,patientBean);
        model.addAttribute("patients", patientProxy.listPatients());
        return "redirect:/";
    }

    @GetMapping("patient/delete-patient/{id}")
    public String deletePatient(@PathVariable("id") Long id, Model model) {
        PatientBean patient = patientProxy.getPatientbyId(id);
        patientProxy.deletePatient(patient.getId());
        List<PatientBean> patients =  patientProxy.listPatients();
        model.addAttribute("patients", patients);
        return "redirect:/";
    }


}