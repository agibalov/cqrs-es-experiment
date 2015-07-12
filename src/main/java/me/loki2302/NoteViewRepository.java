package me.loki2302;

import me.loki2302.views.NoteView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteViewRepository extends JpaRepository<NoteView, String> {
}
