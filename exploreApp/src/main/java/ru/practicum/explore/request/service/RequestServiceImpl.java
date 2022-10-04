package ru.practicum.explore.request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.State;
import ru.practicum.explore.event.service.EventService;
import ru.practicum.explore.exception.*;
import ru.practicum.explore.request.model.Request;
import ru.practicum.explore.request.model.Status;
import ru.practicum.explore.request.repository.RequestRepository;

import java.util.Collection;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final EventService eventService;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, EventService eventService) {
        this.requestRepository = requestRepository;
        this.eventService = eventService;
    }

    /*-------------------------------------- Для владельца события --------------------------------------*/


    @Override
    public Request rejectRequest(long userId, long eventId, long reqId) {
        if (!eventService.isUserOwnerEvent(userId, eventId)) {
            throw new UserDoesNotHaveAccessException("Пользователь не является владельцем события.");
        }
        Request request = getRequestById(reqId);
        request.setStatus(Status.REJECTED);
        return requestRepository.save(request);
    }

    @Override
    public Request confirmRequest(long userId, long eventId, long reqId) {
        if (!eventService.isUserOwnerEvent(userId, eventId)) {
            throw new UserDoesNotHaveAccessException("Пользователь не является владельцем события.");
        }
        Request request = getRequestById(reqId);
        Event event = eventService.getEventById(eventId);
        if (event.getParticipantLimit() == 0 || event.getParticipantLimit() > event.getConfirmedRequests()) {
            request.setStatus(Status.CONFIRMED);
            eventService.increaseEventConfirmedRequests(event);
            requestRepository.save(request);
            if (event.getParticipantLimit() == event.getConfirmedRequests()) {
                rejectAllEventRequestWithStatusPending(eventId);
            }
        }

        return request;
    }

    @Override
    public Request getRequestById(long requestId) {
        return requestRepository.findById(requestId).orElseThrow(() -> new RequestNotFoundException("Запрос не найден"));
    }

    @Override
    public Collection<Request> getEventRequests(long userId, long eventId) {
        if (!eventService.isUserOwnerEvent(userId, eventId)) {
            throw new UserDoesNotHaveAccessException("Пользователь не является владельцем события.");
        }
        return requestRepository.findAllByEventId(eventId);
    }

    private boolean rejectAllEventRequestWithStatusPending(long eventId) {
        Collection<Request> requests = requestRepository.findAllByEventIdAndStatus(eventId, Status.PENDING.toString());
        for (Request request : requests) {
            request.setStatus(Status.REJECTED);
            requestRepository.save(request);
        }
        return true;
    }

    /*-------------------------------------- Для владельца запроса --------------------------------------*/
    @Override
    public Collection<Request> getUserRequests(long userId) {
        return requestRepository.findAllByRequesterId(userId);
    }

    @Override
    public Request cancelRequest(long userId, long requestId) {
        Request request = getRequestById(requestId);
        if (request.getRequesterId() == userId) {
            request.setStatus(Status.CANCELED);
        } else {
            throw new UserDoesNotHaveAccessException("Пользователь не является владельцем запроса");
        }
        return requestRepository.save(request);
    }

    @Override
    public Request addRequest(Request request) {
        Request oldRequest = requestRepository.findByEventIdAndRequesterId(request.getEventId(), request.getRequesterId());
        if (oldRequest != null) {
            if (oldRequest.getStatus() == Status.PENDING || oldRequest.getStatus() == Status.CONFIRMED) {
                throw new DuplicateRequestException("Запрос на данное событие уже существует.");
            }
        }

//        request.setId(null);
        Event event = eventService.getEventById(request.getEventId());

        if (request.getRequesterId() == event.getInitiator().getId()) {
            throw new UserDoesNotHaveAccessException("Владелец события не может быть участником события.");
        }
        if (event.getState() != State.PUBLISHED) {
            throw new EventHasInappropriateStateException("Невозможно отправить заявку на неопубликованное событие.");
        }
        if ((event.getRequestModeration() == false && event.getParticipantLimit() > event.getConfirmedRequests()) || event.getParticipantLimit() == 0) {
            request.setStatus(Status.CONFIRMED);
            eventService.increaseEventConfirmedRequests(event);
        } else if (event.getParticipantLimit() < event.getConfirmedRequests()) {
            throw new MemberLimitExceededException("Превышен лимит участников. Невозможно зарегистрироваться на событие");
        } else {
            request.setStatus(Status.PENDING);
        }

        return requestRepository.save(request);
    }

}
