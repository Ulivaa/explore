package ru.practicum.explore.mapper.request;

import ru.practicum.explore.model.request.Request;
import ru.practicum.explore.model.request.dto.RequestDto;

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
