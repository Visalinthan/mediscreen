package com.mediscreen.client.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteBean {
    private String id;
    private String title;
    private String content;
    private String date;
    public String patientId;
    public String medecinId;
}
