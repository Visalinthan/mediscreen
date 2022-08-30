package com.mediscreen.client.proxies;


import com.mediscreen.client.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-patient", url = "localhost:9001", decode404 = true)
public interface MicroservicePatientProxy {
    @GetMapping(value = "patient/listPatient")
    List<PatientBean> listPatients();

    @GetMapping(value = "/patient/getPatient/{id}")
    PatientBean getPatientbyId(@PathVariable("id") Long id);

    @PostMapping(value = "/patient/addPatient")
    PatientBean addPatient(@RequestBody PatientBean patient);

    @PutMapping(value = "/patient/updatePatient/{id}")
    PatientBean updatePatient(@PathVariable("id") Long id,@RequestBody PatientBean patient);

    @DeleteMapping(value = "/patient/deletePatient/{id}")
    PatientBean deletePatient(@PathVariable("id") Long id);
}

