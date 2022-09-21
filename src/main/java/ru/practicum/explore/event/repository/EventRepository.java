package ru.practicum.explore.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.user.model.User;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
