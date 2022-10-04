package ru.practicum.explore.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.request.model.Request;

import java.util.Collection;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Collection<Request> findAllByRequesterId(long requesterId);

//    @Query(
//            "SELECT r FROM Request r join Event e WHERE e.initiator = ?1"
//    )
//    List<Request> searchRequestsByEvenInitiator(long userId);

    Collection<Request> findAllByEventId(long eventId);
    Collection<Request> findAllByEventIdAndStatus(long eventId, String status);

    Request findByEventIdAndRequesterId(long eventId, long requesterId);
}
