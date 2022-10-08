package ru.practicum.explore.service.event;


import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.Sort;

import java.util.Collection;
import java.util.List;

public interface EventService {
    /**
     * добавить событие
     */
    Event addEvent(Event event, long userId);

    /**
     * обновить событие от имени владельца
     */
    Event updateEventByUser(Event event, long userId);

    /**
     * обновить событие от имени администратора
     */
    Event updateEventByAdmin(long eventId, Event event);

    /**
     * достать все события
     */
    Collection<Event> getEvents();

    /**
     * достать события по фильтрам
     */
    Collection<Event> getFilteredEvents(String text,
                                        List<Long> categories,
                                        Boolean paid,
                                        String rangeStart,
                                        String rangeEnd,
                                        Boolean onlyAvailable,
                                        Sort sort,
                                        int from,
                                        int size);

    /**
     * достать события по фильтрам от имени администратора
     */
    Collection<Event> getEventByAdmin(List<Long> users,
                                      List<String> states,
                                      List<Long> categories,
                                      String rangeStart,
                                      String rangeEnd,
                                      int from,
                                      int size);

    /**
     * достать событие по id от имени администратора
     */
    Event getEventById(long eventId);

    /**
     * достать опубликованное событие
     */
    Event getPublishedEventById(long eventId);

    /**
     * достать событие владельца
     */
    Event getUserEvent(long eventId, long userId);

    /**
     * достать события владельца
     */
    Collection<Event> getUserEvents(long userId, int from, int size);

    /**
     * увеличить на 1 подтвержденные запросы в событии
     */
    Event increaseEventConfirmedRequests(Event eventId);

    /**
     * отменить событие от имени владельца
     */
    Event cancelEvent(long userId, long eventId);

    /**
     * опубликовать событие от имени администратора
     */
    Event publishEvent(long eventId);

    /**
     * отконить событие от имени администратора
     */
    Event rejectEvent(long eventId);

    /**
     * узнать является ли пользователь владельцем события
     */
    Boolean isUserOwnerEvent(long userId, long eventId);

}
