package me.loki2302;

import me.loki2302.events.NoteCreatedDomainEvent;
import me.loki2302.events.NoteDeletedDomainEvent;
import me.loki2302.events.NoteDomainEvent;
import me.loki2302.events.NoteUpdatedDomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteAggregateRepository {
    @Autowired
    private NoteDomainEventRepository noteDomainEventRepository;

    public NoteAggregate findOne(String id) {
        NoteAggregate note = null;

        List<NoteDomainEvent> events = noteDomainEventRepository.findByNoteId(id);
        for(NoteDomainEvent event : events) {
            if(event instanceof NoteCreatedDomainEvent) {
                if(note != null) {
                    throw new IllegalStateException();
                }

                NoteCreatedDomainEvent e = (NoteCreatedDomainEvent)event;
                note = new NoteAggregate();
                note.id = e.noteId;
                note.text = e.text;
            } else if(event instanceof NoteUpdatedDomainEvent) {
                if(note == null) {
                    throw new IllegalStateException();
                }

                NoteUpdatedDomainEvent e = (NoteUpdatedDomainEvent)event;
                note.text = e.text;
            } else if(event instanceof NoteDeletedDomainEvent) {
                if(note == null) {
                    throw new IllegalStateException();
                }

                note = null;
            } else {
                throw new RuntimeException("Unknown event " + event.getClass());
            }
        }

        // either null if there's no such note, or note if there is
        return note;
    }
}
