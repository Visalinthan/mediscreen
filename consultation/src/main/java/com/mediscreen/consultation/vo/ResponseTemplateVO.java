package com.mediscreen.consultation.vo;

import com.mediscreen.consultation.model.Notes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Notes notes;
   // private Medecin medecin;
    private Patient patient;
}
