package com.mediscreen.consultation.proxies;

import com.mediscreen.consultation.vo.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient", url = "localhost:9191", decode404 = true)
public interface MicroservicePatientProxy {
    @GetMapping(value = "patient/listPatient")
    List<Patient> listPatients();

    @GetMapping(value = "/patient/getPatient/{id}")
    Patient getPatientbyId(@PathVariable("id") Long id);

    @GetMapping(value = "/patient/getAge/{id}")
    int getAgePatient(@PathVariable("id") Long id);
}

