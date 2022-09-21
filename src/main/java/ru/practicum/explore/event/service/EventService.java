package ru.practicum.explore.event.service;


import ru.practicum.explore.event.model.Event;

import java.util.Collection;

public interface EventService {
    Event addEvent(Event event, long userId);

    Event updateEvent(Event event, long userId);

    Collection<Event> getEvents();

    Event getEventById(long eventId);

    Collection<Event> getUserEvents();

    Event cancelEvent(long eventId);


}
