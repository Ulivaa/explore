package ru.practicum.explore.request.service;

import ru.practicum.explore.request.model.Request;
import ru.practicum.explore.request.model.RequestDto;

import java.time.LocalDateTime;

public class RequestMapper {
    public static Request toRequest(long userId, long eventId) {
        return Request.builder()
                .created(LocalDateTime.now())
                .eventId(eventId)
                .requesterId(userId)
                .build();
    }

    public static RequestDto toRequestDto(Request request) {
        return new RequestDto(request.getId(),
                request.getEventId(),
                request.getRequesterId(),
                request.getStatus(),
                request.getCreated());
    }
}
