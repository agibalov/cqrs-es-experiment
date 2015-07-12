package me.loki2302;

import me.loki2302.events.NoteDomainEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteDomainEventRepository extends JpaRepository<NoteDomainEvent, Long> {
    List<NoteDomainEvent> findByNoteId(String noteId);
}
