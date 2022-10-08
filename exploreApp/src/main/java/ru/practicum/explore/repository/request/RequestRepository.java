package ru.practicum.explore.repository.request;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.model.request.Request;

import java.util.Collection;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Collection<Request> findAllByRequesterId(long requesterId);

    Collection<Request> findAllByEventId(long eventId);

    Collection<Request> findAllByEventIdAndStatus(long eventId, String status);

    Request findByEventIdAndRequesterId(long eventId, long requesterId);
}
