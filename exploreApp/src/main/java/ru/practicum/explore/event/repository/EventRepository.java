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

    /*----------------------------------- ФИЧА -----------------------------------*/
    Collection<Event> findAllByCategory_idInOrderByEventDate(List<Long> categories);

    Collection<Event> findAllByStateOrderByEventDate(State state);
    /*----------------------------------- ФИЧА -----------------------------------*/


    @Query(value = "SELECT * FROM events e " +
            "WHERE " +
            "(e.state = 'PUBLISHED') and " +
            "((:isCategories = false) or (e.category_id in :categories) ) and " +
            "(e.event_date between :rangeStart and :rangeEnd) and" +
            "(e.participant_limit > confirmed_requests or :onlyAvailable is null ) and " +
            "((lower(e.annotation) like %:text% or lower(e.description) like %:text%) or :text is null) and " +
            "(e.paid = :paid or :paid is null)",
            nativeQuery = true)
    Collection<Event> find(String text,
                           Boolean isCategories,
                           List<Long> categories,
                           Boolean paid,
                           LocalDateTime rangeStart,
                           LocalDateTime rangeEnd,
                           Boolean onlyAvailable,
                           PageRequest pageRequest);

    @Query(value = "SELECT * FROM events e " +
            "WHERE " +
            "(e.state in :states or :isStates = false ) and " +
            "( e.category_id in :categories or :isCat = false) and " +
            "(e.initiator_id in :initiators or :isUsers = false) and" +
            "(e.event_date between :rangeStart and :rangeEnd) ",
            nativeQuery = true)
    Collection<Event> findByAdmin(boolean isUsers, List<Long> initiators, boolean isStates,
                                  List<String> states, boolean isCat,
                                  List<Long> categories,
                                  LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd,
                                  PageRequest pageRequest);

}
