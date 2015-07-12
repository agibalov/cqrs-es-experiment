package me.loki2302;

import me.loki2302.commands.CreateNoteCommand;
import me.loki2302.commands.DeleteNoteCommand;
import me.loki2302.commands.UpdateNoteCommand;
import me.loki2302.events.NoteDomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceFacade {
    @Autowired
    private NoteCommandHandler noteCommandHandler;

    @Autowired
    private NoteDomainEventRepository noteDomainEventRepository;

    public void handleCommand(CreateNoteCommand command) {
        List<NoteDomainEvent> events = noteCommandHandler.handleCommand(command);
        noteDomainEventRepository.save(events);
    }

    public void handleCommand(UpdateNoteCommand command) {
        List<NoteDomainEvent> events = noteCommandHandler.handleCommand(command);
        noteDomainEventRepository.save(events);
    }

    public void handleCommand(DeleteNoteCommand command) {
        List<NoteDomainEvent> events = noteCommandHandler.handleCommand(command);
        noteDomainEventRepository.save(events);
    }
}
