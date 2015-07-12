package me.loki2302;

import me.loki2302.commands.CreateNoteCommand;
import me.loki2302.commands.DeleteNoteCommand;
import me.loki2302.commands.UpdateNoteCommand;
import me.loki2302.entities.NoteCreatedDomainEvent;
import me.loki2302.entities.NoteDeletedDomainEvent;
import me.loki2302.entities.NoteDomainEvent;
import me.loki2302.entities.NoteUpdatedDomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteDomainEventRepository noteDomainEventRepository;

    public Note getNoteById(String id) {
        Note note = null;

        List<NoteDomainEvent> events = noteDomainEventRepository.findByNoteId(id);
        for(NoteDomainEvent event : events) {
            if(event instanceof NoteCreatedDomainEvent) {
                if(note != null) {
                    throw new IllegalStateException();
                }

                NoteCreatedDomainEvent e = (NoteCreatedDomainEvent)event;
                note = new Note();
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

    public Note handleCommand(CreateNoteCommand command) {
        // TODO: check if note does not exist

        NoteCreatedDomainEvent e = new NoteCreatedDomainEvent();
        e.noteId = command.id;
        e.text = command.text;
        noteDomainEventRepository.save(e);

        Note note = new Note();
        note.id = command.id;
        note.text = command.text;
        return note;
    }

    public Note handleCommand(UpdateNoteCommand command) {
        // TODO: check if note exists

        NoteUpdatedDomainEvent e = new NoteUpdatedDomainEvent();
        e.noteId = command.id;
        e.text = command.text;
        noteDomainEventRepository.save(e);

        Note note = new Note();
        note.id = command.id;
        note.text = command.text;
        return note;
    }

    public void handleCommand(DeleteNoteCommand command) {
        // TODO: check if note exists

        NoteDeletedDomainEvent e = new NoteDeletedDomainEvent();
        e.noteId = command.id;
        noteDomainEventRepository.save(e);
    }
}
