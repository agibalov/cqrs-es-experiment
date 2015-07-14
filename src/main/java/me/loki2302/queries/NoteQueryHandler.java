package me.loki2302.queries;

import me.loki2302.views.NoteViewRepository;
import me.loki2302.views.NoteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteQueryHandler {
    @Autowired
    private NoteViewRepository noteViewRepository;

    public NoteDTO handle(GetByIdNoteQuery query) {
        NoteView noteView = noteViewRepository.findOne(query.id);
        if(noteView == null) {
            return null;
        }

        return noteDTOFromNoteView(noteView);
    }

    public List<NoteDTO> handle(GetAllNoteQuery query) {
        List<NoteView> noteViews = noteViewRepository.findAll();
        return noteDTOsFromNoteViews(noteViews);
    }

    private static NoteDTO noteDTOFromNoteView(NoteView noteView) {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.id = noteView.id;
        noteDTO.text = noteView.text;
        noteDTO.textLength = noteView.textLength;
        return noteDTO;
    }

    private static List<NoteDTO> noteDTOsFromNoteViews(List<NoteView> noteViews) {
        List<NoteDTO> noteDTOs = new ArrayList<NoteDTO>();
        for(NoteView noteView : noteViews) {
            NoteDTO noteDTO = noteDTOFromNoteView(noteView);
            noteDTOs.add(noteDTO);
        }
        return noteDTOs;
    }
}
