package me.loki2302.queries;

import me.loki2302.NoteAggregate;
import me.loki2302.NoteAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteQueryHandler {
    // TODO: replace with some sort of materialized view
    @Autowired
    private NoteAggregateRepository noteAggregateRepository;

    public NoteDTO handle(GetByIdNoteQuery query) {
        NoteAggregate noteAggregate = noteAggregateRepository.findOne(query.id);
        if(noteAggregate == null) {
            return null;
        }

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.id = noteAggregate.id;
        noteDTO.text = noteAggregate.text;
        return noteDTO;
    }
}
