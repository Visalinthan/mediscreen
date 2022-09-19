package com.mediscreen.client.mapper;

import com.mediscreen.client.beans.NoteBean;
import com.mediscreen.client.dto.NoteDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NoteMapper {

    public NoteDto toDto(NoteBean noteBean){
        NoteDto noteDto = new NoteDto();
        //noteDto.setId(noteBean.getId());
        noteDto.setTitle(noteBean.getTitle());
        noteDto.setContent(noteBean.getContent());
        return noteDto;
    }

    public NoteBean toEntity(NoteDto noteDto, String id){
        NoteBean noteBean = new NoteBean();

        String dateStr = LocalDate.now().toString();
        noteBean.setTitle(noteDto.getTitle());
        noteBean.setContent(noteDto.getContent());
        noteBean.setDate(dateStr);
        noteBean.setPatientId(id);

        return noteBean;
    }
}
