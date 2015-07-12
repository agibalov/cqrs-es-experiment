package me.loki2302.queries;

import me.loki2302.NoteViewRepository;
import me.loki2302.views.NoteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteQueryHandler {
    @Autowired
    private NoteViewRepository noteViewRepository;

    public NoteDTO handle(GetByIdNoteQuery query) {
        NoteView noteView = noteViewRepository.findOne(query.id);
        if(noteView == null) {
            return null;
        }

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.id = noteView.id;
        noteDTO.text = noteView.text;
        return noteDTO;
    }
}
