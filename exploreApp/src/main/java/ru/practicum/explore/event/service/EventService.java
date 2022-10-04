package ru.practicum.explore.event.service;


import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.Sort;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface EventService {
    Event addEvent(Event event, long userId);

    Event updateEventByUser(Event event, long userId);

    Event updateEventByAdmin(long eventId, Event event);

    Collection<Event> getEvents();

    Collection<Event> getFilteredEvents(String text,
                                        List<Long> categories,
                                        Boolean paid,
                                        LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd,
                                        Boolean onlyAvailable,
                                        Sort sort,
                                        int from,
                                        int size);


    Collection<Event> getEventByAdmin(Integer[] users,
                                      String[] states,
                                      Integer[] categories,
                                      LocalDateTime rangeStart,
                                      LocalDateTime rangeEnd,
                                      int from,
                                      int size);

    Event getEventById(long eventId);

    Event getPublishedEventById(long eventId);

    Event getUserEvent(long eventId, long userId);

    Collection<Event> getUserEvents(long userId, int from, int size);

    Event increaseEventConfirmedRequests(Event eventId);

    Event cancelEvent(long userId, long eventId);

    Event publishEvent(long eventId);

    Event rejectEvent(long eventId);

     Boolean isUserOwnerEvent(long userId, long eventId);

    Collection<Event> getEventsByCategory(long catId);


}
