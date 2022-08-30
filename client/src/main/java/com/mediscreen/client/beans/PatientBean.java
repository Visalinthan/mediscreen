package com.mediscreen.client.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {
    private Long id;
    private String firstName;
    private String lastName;
    private String sex;
    private String dob;
    private String address;
    private int phone;
}
