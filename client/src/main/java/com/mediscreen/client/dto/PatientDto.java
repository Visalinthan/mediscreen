package com.mediscreen.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private String id;
    private String firstName;
    private String lastName;
    private String sex;
    private String dob;
    private String address;
    private int phone;
}
