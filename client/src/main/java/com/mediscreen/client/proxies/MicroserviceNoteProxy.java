package com.mediscreen.client.proxies;

import com.mediscreen.client.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "microservice-consultation", url = "localhost:9191")
public interface MicroserviceNoteProxy {

    @GetMapping(value="/consultation/notesByPatient/{id}")
    List<NoteBean> listNoteByPatientId(@PathVariable("id") String id);

    @GetMapping(value = "/consultation//getNote/{id}")
    Optional<NoteBean> getNotesById(@PathVariable String id);

    @PostMapping(value ="/consultation/addNote/{id}")
    NoteBean addNote(@PathVariable("id") String id, @RequestBody NoteBean notes);

    @PutMapping(value ="/consultation/updateNotes/{id}")
    NoteBean updateNotes(@PathVariable("id") String id,@RequestBody NoteBean notes);

    @DeleteMapping("/consultation/deleteNotes/{id}")
    NoteBean deleteNotes(@PathVariable String id);

    @GetMapping(value ="/consultation/reportPatient/{id}")
    String reportPatient(@PathVariable("id") String id);
}
