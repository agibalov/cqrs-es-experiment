package me.loki2302.events;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("noteDeleted")
public class NoteDeletedDomainEvent extends NoteDomainEvent {
}
