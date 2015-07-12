package me.loki2302.events;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("noteCreated")
public class NoteCreatedDomainEvent extends NoteDomainEvent {
    public String text;
}
