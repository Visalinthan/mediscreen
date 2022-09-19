package com.mediscreen.client.controller;

import com.mediscreen.client.beans.NoteBean;
import com.mediscreen.client.beans.PatientBean;
import com.mediscreen.client.dto.NoteDto;
import com.mediscreen.client.mapper.NoteMapper;
import com.mediscreen.client.proxies.MicroserviceNoteProxy;
import com.mediscreen.client.proxies.MicroservicePatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {

    private final MicroserviceNoteProxy noteProxy;
    private final MicroservicePatientProxy patientProxy;
    private final NoteMapper noteMapper;

    public NoteController(MicroserviceNoteProxy noteProxy, MicroservicePatientProxy patientProxy, NoteMapper noteMapper) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
        this.noteMapper = noteMapper;
    }


    @GetMapping("/notes/add-notes/{id}")
    public String addNoteForm(@PathVariable("id") Long id, Model model) {
        PatientBean patient = patientProxy.getPatientbyId(id);
        model.addAttribute("patient", patient);
        model.addAttribute("noteDto",new NoteDto());
        return "notes/add-notes";
    }

    @PostMapping("/notes/validate/{id}")
    public String validateNote(@PathVariable Long id, @Valid NoteDto noteDto, BindingResult result, Model model) {
        String StrId = Long.toString(id);

        if (!result.hasErrors()) {
            NoteBean noteBean = noteMapper.toEntity(noteDto,StrId);
            noteProxy.addNote(StrId,noteBean);
            PatientBean patient = patientProxy.getPatientbyId(id);
            List<NoteBean> notes =  noteProxy.listNoteByPatientId(StrId);
            model.addAttribute("notes", notes);
            model.addAttribute("patient", patient);
            return "redirect:/patient/get-patient/"+id;
        }
        return "notes/add-notes";
    }

    @GetMapping("/notes/update-notes/{id}")
    public String updateNoteForm(@PathVariable("id") String id, Model model) {
        Optional<NoteBean> noteBean = noteProxy.getNotesById(id);
        Long patientId = Long.parseLong(noteBean.get().getPatientId());
        PatientBean patient = patientProxy.getPatientbyId(patientId);
        model.addAttribute("notes", noteBean.get());
        model.addAttribute("patient", patient);
        return "notes/update-notes";
    }

    @PostMapping("/notes/update-notes/{id}")
    public String updateNoteById(@PathVariable("id") String id, @Valid NoteBean noteBean,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "notes/update-notes";
        }
        Optional<NoteBean> noteBeanFind = noteProxy.getNotesById(id);
        noteProxy.updateNotes(id,noteBean);
        model.addAttribute("notes", noteProxy.listNoteByPatientId(noteBeanFind.get().getPatientId()));
        return "redirect:/patient/get-patient/"+noteBeanFind.get().getPatientId()+"";
    }

    @GetMapping("notes/delete-notes/{id}")
    public String deletePatient(@PathVariable("id") String id, Model model) {
        Optional<NoteBean> noteBean = noteProxy.getNotesById(id);
        Long patientId = Long.parseLong(noteBean.get().getPatientId());
        PatientBean patient = patientProxy.getPatientbyId(patientId);
        noteProxy.deleteNotes(id);
        model.addAttribute("patient", patient);
        return "redirect:/patient/get-patient/"+patientId+"";
    }

}
