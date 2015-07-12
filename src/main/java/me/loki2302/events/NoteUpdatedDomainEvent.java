package me.loki2302.events;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("noteUpdated")
public class NoteUpdatedDomainEvent extends NoteDomainEvent {
    public String text;
}
