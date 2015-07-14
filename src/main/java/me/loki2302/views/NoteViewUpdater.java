package me.loki2302.views;

import me.loki2302.events.NoteCreatedDomainEvent;
import me.loki2302.events.NoteDeletedDomainEvent;
import me.loki2302.events.NoteDomainEvent;
import me.loki2302.events.NoteUpdatedDomainEvent;
import me.loki2302.views.NoteView;
import me.loki2302.views.NoteViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteViewUpdater {
    @Autowired
    private NoteViewRepository noteViewRepository;

    public void handleEvents(List<NoteDomainEvent> events) {
        for(NoteDomainEvent event : events) {
            handleEvent(event);
        }
    }

    public void handleEvent(NoteDomainEvent event) {
        if(event instanceof NoteCreatedDomainEvent) {
            handleEvent((NoteCreatedDomainEvent)event);
        } else if(event instanceof NoteUpdatedDomainEvent) {
            handleEvent((NoteUpdatedDomainEvent)event);
        } else if(event instanceof NoteDeletedDomainEvent) {
            handleEvent((NoteDeletedDomainEvent)event);
        } else {
            throw new RuntimeException("Unknown event type");
        }
    }

    public void handleEvent(NoteCreatedDomainEvent event) {
        NoteView existingNoteView = noteViewRepository.findOne(event.noteId);
        if(existingNoteView != null) {
            throw new RuntimeException("Inconsistency");
        }

        NoteView noteView = new NoteView();
        noteView.id = event.noteId;
        noteView.text = event.text;
        noteView.textLength = event.text.length();
        noteViewRepository.save(noteView);
    }

    public void handleEvent(NoteUpdatedDomainEvent event) {
        NoteView noteView = noteViewRepository.findOne(event.noteId);
        if(noteView == null) {
            throw new RuntimeException("Inconsistency");
        }

        noteView.text = event.text;
        noteView.textLength = event.text.length();
        noteViewRepository.save(noteView);
    }

    public void handleEvent(NoteDeletedDomainEvent event) {
        NoteView noteView = noteViewRepository.findOne(event.noteId);
        if(noteView == null) {
            throw new RuntimeException("Inconsistency");
        }

        noteViewRepository.delete(noteView);
    }
}
