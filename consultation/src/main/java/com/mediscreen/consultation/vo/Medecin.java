package com.mediscreen.consultation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medecin {
    private Long id;
    private String firstName;
    private String lastName;
}
