package ru.practicum.explore.request.service;

import ru.practicum.explore.request.model.Request;

import java.util.Collection;

public interface RequestService {
    Request addRequest(Request request);

    Request cancelRequest(long userId, long requestId);

    Request rejectRequest(long userId, long eventId, long reqId);

    Request confirmRequest(long userId, long eventId, long reqId);

    Request getRequestById(long requestId);

    Collection<Request> getUserRequests(long userId);

    Collection<Request> getEventRequests(long userId, long eventId);
}
