package me.loki2302.views;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NoteView {
    @Id
    public String id;
    public String text;
}
