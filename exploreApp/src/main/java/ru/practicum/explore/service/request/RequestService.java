package ru.practicum.explore.service.request;

import ru.practicum.explore.model.request.Request;

import java.util.Collection;

public interface RequestService {
    /**
     * добавить запрос на событие
     */
    Request addRequest(Request request);

    /**
     * отменить запрос на событие
     */
    Request cancelRequest(long userId, long requestId);

    /**
     * отклонить запрос на событие от имени владельца
     */
    Request rejectRequest(long userId, long eventId, long reqId);

    /**
     * подтвердить запрос на событие от имени владельца
     */
    Request confirmRequest(long userId, long eventId, long reqId);

    /**
     * достать запрос на событие по id
     */
    Request getRequestById(long requestId);

    /**
     * достать запросы пользователя на событие
     */
    Collection<Request> getUserRequests(long userId);

    /**
     * достать запросы на событие к текущему событию
     */
    Collection<Request> getEventRequests(long userId, long eventId);
}
