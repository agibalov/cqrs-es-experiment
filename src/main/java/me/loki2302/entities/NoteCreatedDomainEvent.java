package me.loki2302.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("noteCreated")
public class NoteCreatedDomainEvent extends NoteDomainEvent {
    public String text;
}
