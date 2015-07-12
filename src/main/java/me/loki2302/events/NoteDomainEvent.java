package me.loki2302.events;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class NoteDomainEvent {
    @Id
    @GeneratedValue
    public Long id;
    public String noteId;
}
