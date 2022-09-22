package ru.practicum.explore.event.service;

import org.springframework.stereotype.Service;
import ru.practicum.explore.category.service.CategoryService;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.State;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.exception.EventNotFoundException;
import ru.practicum.explore.exception.EventNotHaveStatePendingException;
import ru.practicum.explore.exception.UserDoesNotHaveAccessException;
import ru.practicum.explore.user.service.UserService;

import java.util.Collection;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public EventServiceImpl(EventRepository eventRepository, UserService userService, CategoryService categoryService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public Event addEvent(Event event, long userId) {
        event.setInitiator(userService.getUserById(userId));
        event.setCategory(categoryService.getCategoryById(event.getCategory().getId()));
//        event.setState(State.PENDING);
//        eventRepository.save(event);
        return eventRepository.save(event);

    }

    @Override
    public Event updateEvent(Event eventUpdate, long userId) {
        Event event = getEventById(eventUpdate.getId());
//        сдедать метод отдельный
        if (event.getInitiator().getId() != userId) {
            throw new UserDoesNotHaveAccessException("Пользователь не является владельцем события.");
        }
        if (eventUpdate.getAnnotation()!=null){
            event.setAnnotation(eventUpdate.getAnnotation());
        }
        if (eventUpdate.getEventDate()!=null){
            event.setEventDate(eventUpdate.getEventDate());
        }
        if (eventUpdate.getTitle()!=null){
            event.setTitle(eventUpdate.getTitle());
        }
        if (eventUpdate.getCategory()!=null){
            event.setCategory(categoryService.getCategoryById(eventUpdate.getCategory().getId()));
        }
        if (eventUpdate.getDescription()!=null){
            event.setDescription(eventUpdate.getDescription());
        }
        if (eventUpdate.getLat()!=null){
            event.setLat(eventUpdate.getLat());
        }
        if (eventUpdate.getLon()!=null){
            event.setLon(eventUpdate.getLon());
        }
        if (eventUpdate.getPaid()!=null){
            event.setPaid(eventUpdate.getPaid());
        }
        if (eventUpdate.getParticipantLimit()!=null){
            event.setParticipantLimit(eventUpdate.getParticipantLimit());
        }
        if (eventUpdate.getRequestModeration()!=null){
            event.setRequestModeration(eventUpdate.getRequestModeration());
        }
        return eventRepository.save(event);
    }

    @Override
    public Collection<Event> getEvents() {
        return null;
    }

    @Override
    public Event getEventById(long eventId) {
        return eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("Событие не найдено"));
    }

    @Override
    public Collection<Event> getUserEvents() {
        return null;
    }

    @Override
    public Event cancelEvent(long userId, long eventId) {
        Event event = getEventById(eventId);
        if (event.getInitiator().getId() != userId) {
            throw new UserDoesNotHaveAccessException("Пользователь не является владельцем события.");
        }
        if (event.getState() != State.PENDING) {
            throw new EventNotHaveStatePendingException("Событие уже опубликовано или отменено.");

        }
        event.setState(State.CANCELED);
        return eventRepository.save(event);
    }
}
