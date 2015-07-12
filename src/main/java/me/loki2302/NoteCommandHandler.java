package me.loki2302;

import me.loki2302.commands.CreateNoteCommand;
import me.loki2302.commands.DeleteNoteCommand;
import me.loki2302.commands.UpdateNoteCommand;
import me.loki2302.events.NoteCreatedDomainEvent;
import me.loki2302.events.NoteDeletedDomainEvent;
import me.loki2302.events.NoteDomainEvent;
import me.loki2302.events.NoteUpdatedDomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NoteCommandHandler {
    @Autowired
    private NoteAggregateRepository noteAggregateRepository;

    public List<NoteDomainEvent> handleCommand(CreateNoteCommand command) {
        String noteId = command.id;
        NoteAggregate existingNote = noteAggregateRepository.findOne(noteId);
        if(existingNote != null) {
            throw new RuntimeException("Note already exists");
        }

        NoteCreatedDomainEvent e = new NoteCreatedDomainEvent();
        e.noteId = command.id;
        e.text = command.text;

        return Arrays.<NoteDomainEvent>asList(e);
    }

    public List<NoteDomainEvent> handleCommand(UpdateNoteCommand command) {
        String noteId = command.id;
        NoteAggregate existingNote = noteAggregateRepository.findOne(noteId);
        if(existingNote == null) {
            throw new RuntimeException("Note does not exist");
        }

        NoteUpdatedDomainEvent e = new NoteUpdatedDomainEvent();
        e.noteId = command.id;
        e.text = command.text;

        return Arrays.<NoteDomainEvent>asList(e);
    }

    public List<NoteDomainEvent> handleCommand(DeleteNoteCommand command) {
        String noteId = command.id;
        NoteAggregate existingNote = noteAggregateRepository.findOne(noteId);
        if(existingNote == null) {
            throw new RuntimeException("Note does not exist");
        }

        NoteDeletedDomainEvent e = new NoteDeletedDomainEvent();
        e.noteId = command.id;

        return Arrays.<NoteDomainEvent>asList(e);
    }
}
