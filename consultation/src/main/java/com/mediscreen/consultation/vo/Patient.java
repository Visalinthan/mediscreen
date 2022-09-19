package com.mediscreen.consultation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    private Long id;
    private String firstName;
    private String lastName;
    private String sex;
    private LocalDate dob;
    private String address;
    private int phone;
}
