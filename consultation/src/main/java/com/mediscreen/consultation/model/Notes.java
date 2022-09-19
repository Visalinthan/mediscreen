package com.mediscreen.consultation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notes {

    @Id
    private String id;
    private String title;
    private String content;
    private String date;
    private String patientId;
    //public String medecinId;


}
