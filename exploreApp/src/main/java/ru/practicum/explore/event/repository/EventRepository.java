package ru.practicum.explore.event.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.State;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByInitiator_Id(long initiator, PageRequest pageRequest);

    Event findByIdAndState(long eventId, State state);

    Collection<Event> findByCategory_Id(long catId);

    Event findByIdAndInitiator_Id(long eventId, long initiator);

//    TODO разобраться с запросом
    @Query(value = "SELECT * FROM events e " +
            "WHERE " +
            "(e.state = 'PUBLISHED') and " +
            "((:isCategories = false) or (e.category_id in :categories) ) and " +
//            "(eventDate >= :rangeStart or :rangeStart is null ) and " +
//            "(eventDate < :rangeEnd or :rangeEnd is null ) and" +
            "(e.participant_limit > confirmed_requests or :onlyAvailable is null ) and " +
            "((lower(e.annotation) like %:text% or lower(e.description) like %:text%) or :text is null) and " +
            "(e.paid = :paid or :paid is null)", nativeQuery = true)
    Collection<Event> find(
            String text,
            Boolean isCategories, List<Long> categories,
            Boolean paid,
//                           LocalDateTime rangeStart,
//                           LocalDateTime rangeEnd,
            Boolean onlyAvailable,
            PageRequest pageRequest
    );

//    TODO разобраться с запросом
    @Query("SELECT e FROM Event e " +
            "WHERE " +
            "(e.category in :categories or :categories is null ) and " +
            "(e.state in :states or :states is null ) and " +
            "(e.initiator in :users or :users is null ) and " +
            "(e.eventDate >= :rangeStart or :rangeStart is null ) and " +
            "(e.eventDate < :rangeEnd or :rangeEnd is null )")
    Collection<Event> findByAdmin(Integer[] users,
                                  String[] states,
                                  Integer[] categories,
                                  LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd,
                                  PageRequest pageRequest);

}
