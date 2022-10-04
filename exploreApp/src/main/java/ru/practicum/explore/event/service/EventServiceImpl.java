package ru.practicum.explore.event.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.service.CategoryService;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.Sort;
import ru.practicum.explore.event.model.State;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.exception.*;
import ru.practicum.explore.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        event.setId(null);
        isDateAfterTwoHours(event.getEventDate());
        event.setInitiator(userService.getUserById(userId));
        event.setCategory(categoryService.getCategoryById(event.getCategory().getId()));
        return eventRepository.save(event);
    }


    @Override
    public Event updateEventByUser(Event newEvent, long userId) {
        Event event = getEventById(newEvent.getId());
        if (event.getInitiator().getId() != userId) {
            throw new UserDoesNotHaveAccessException("Пользователь не является владельцем события.");
        }
        if (event.getState() == State.PENDING) {
            if (event.getState() == State.CANCELED || event.getState() == State.REJECTED) {
                event.setState(State.PENDING);
            }
            return eventRepository.save(updateEventFields(event, newEvent));
        } else {
            throw new EventHasInappropriateStateException("Событие уже опубликовано.");
        }
    }

    @Override
    public Event updateEventByAdmin(long eventId, Event newEvent) {
        Event event = getEventById(eventId);
        return eventRepository.save(updateEventFields(event, newEvent));
    }


    @Override
    public Event increaseEventConfirmedRequests(Event event) {
        event.setConfirmedRequests(event.getConfirmedRequests() + 1);
        return eventRepository.save(event);
    }

    @Override
    public Collection<Event> getEvents() {
        return null;
    }

    @Override
    public Collection<Event> getFilteredEvents(String text,
                                               List<Long> categories,
                                               Boolean paid,
                                               LocalDateTime rangeStart,
                                               LocalDateTime rangeEnd,
                                               Boolean onlyAvailable,
                                               Sort sort,
                                               int from,
                                               int size) {

        if (text != null) {
            text.toLowerCase();
        }
        Boolean isCategories=false;
        if (categories != null){
            isCategories = true;
        }
        var events = eventRepository.find(
                text, isCategories,
                categories, paid, onlyAvailable, PageRequest.of(from, size)
        );

        if (rangeEnd == null) {
            events.stream().filter(event -> event.getEventDate().isAfter(rangeStart == null ? LocalDateTime.now() : rangeStart)).collect(Collectors.toList());
        } else {
            events.stream().filter(event -> event.getEventDate().isAfter(rangeStart == null ? LocalDateTime.now() : rangeStart) && event.getEventDate().isBefore(rangeEnd)).collect(Collectors.toList());

        }
        if (sort != null) {
            if (sort == Sort.EVENT_DATE) {
                events.stream().sorted(Comparator.comparing(Event::getEventDate));
            }
            if (sort == Sort.VIEWS) {
                events.stream().sorted(Comparator.comparing(Event::getEventDate));
            }
        }
        return events;
    }

    @Override
    public Collection<Event> getEventByAdmin(Integer[] users, String[] states, Integer[] categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size) {
        if (rangeStart == null) {
            rangeStart = LocalDateTime.now();
        }
        return eventRepository.findByAdmin(users, states, categories, rangeStart, rangeEnd, PageRequest.of(from, size));
    }

    @Override
    public Event getEventById(long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Событие не найдено."));
    }


    public Event getPublishedEventById(long eventId) {
        Event event= eventRepository.findByIdAndState(eventId, State.PUBLISHED);
        if (event==null) {
            throw new EventNotFoundException("Событие не найдено.");
        }
        return event;
    }

    @Override
    public Event getUserEvent(long eventId, long userId) {
//        TODO обработать исключение
        return eventRepository.findByIdAndInitiator_Id(eventId, userId);
    }

    @Override
    public Collection<Event> getUserEvents(long userId, int from, int size) {
        return eventRepository.findAllByInitiator_Id(userId, PageRequest.of(from, size));
    }

    @Override
    public Event cancelEvent(long userId, long eventId) {
        Event event = getEventById(eventId);
        if (!isUserOwnerEvent(userId, eventId)) {
            throw new UserDoesNotHaveAccessException("Пользователь не является владельцем события.");
        }
        if (event.getState() != State.PENDING) {
            throw new EventHasInappropriateStateException("Событие уже опубликовано или отменено.");

        }
        event.setState(State.CANCELED);
        return eventRepository.save(event);
    }

    @Override
    public Event publishEvent(long eventId) {
        Event event = getEventById(eventId);
        if (event.getState() != State.PENDING) {
            throw new EventHasInappropriateStateException("Опубликовать можно только события в состоянии ожидания.");
        }
        if (event.getEventDate().plusHours(1).isBefore(LocalDateTime.now())) {
            throw new EventDateIsOverdueException("Событие просрочено");
        }
        event.setPublishedOn(LocalDateTime.now());
        event.setState(State.PUBLISHED);
        return eventRepository.save(event);
    }

    @Override
    public Event rejectEvent(long eventId) {
        Event event = getEventById(eventId);
        if (event.getState() != State.PENDING) {
            throw new EventHasInappropriateStateException("Отклонить можно только события в состоянии ожидания.");
        }
        event.setState(State.REJECTED);
        return eventRepository.save(event);
    }

    public Boolean isUserOwnerEvent(long userId, long eventId) {
        Event event = getEventById(eventId);
        if (event.getInitiator().getId() != userId) {
            return false;
        }
        return true;
    }

    public Collection<Event> getEventsByCategory(long catId) {
        return eventRepository.findByCategory_Id(catId);
    }

    private Event updateEventFields(Event event, Event newEvent) {
        if (newEvent.getAnnotation() != null) {
            event.setAnnotation(newEvent.getAnnotation());
        }
        if (newEvent.getEventDate() != null) {
            if (isDateAfterTwoHours(event.getEventDate())) {
                event.setEventDate(newEvent.getEventDate());
            }
        }
        if (newEvent.getTitle() != null) {
            event.setTitle(newEvent.getTitle());
        }
        if (newEvent.getCategory() != null) {
            event.setCategory(categoryService.getCategoryById(newEvent.getCategory().getId()));
        }
        if (newEvent.getDescription() != null) {
            event.setDescription(newEvent.getDescription());
        }
        if (newEvent.getLat() != null) {
            event.setLat(newEvent.getLat());
        }
        if (newEvent.getLon() != null) {
            event.setLon(newEvent.getLon());
        }
        if (newEvent.getPaid() != null) {
            event.setPaid(newEvent.getPaid());
        }
        if (newEvent.getParticipantLimit() != null) {
            event.setParticipantLimit(newEvent.getParticipantLimit());
        }
        if (newEvent.getRequestModeration() != null) {
            event.setRequestModeration(newEvent.getRequestModeration());
        }
        return event;
    }

    private boolean isDateAfterTwoHours(LocalDateTime date) {
        if (date.isAfter(LocalDateTime.now().plusHours(2))) {
            return true;
        } else {
            throw new IncorrectParameterException("Дата события не может быть раньше " + LocalDateTime.now().plusHours(2));

        }

    }
}
