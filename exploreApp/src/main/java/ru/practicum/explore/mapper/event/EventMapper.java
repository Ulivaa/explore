package ru.practicum.explore.mapper.event;

import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventInDto;
import ru.practicum.explore.model.event.dto.EventShortDto;
import ru.practicum.explore.mapper.user.UserMapper;

import java.time.LocalDateTime;

public class EventMapper {

    public static Event toEvent(EventInDto eventInDto) {
        return Event.builder()
                .annotation(eventInDto.getAnnotation())
                .id(eventInDto.getId())
                .category(Category.builder().id(eventInDto.getCategory()).build())
                .eventDate(eventInDto.getEventDate())
                .description(eventInDto.getDescription())
                .createdOn(LocalDateTime.now())
                .lat(eventInDto.getLocation().getLat())
                .lon(eventInDto.getLocation().getLon())
                .paid(eventInDto.getPaid())
                .participantLimit(eventInDto.getParticipantLimit())
                .requestModeration(eventInDto.getRequestModeration())
                .confirmedRequests(0L)
                .state(State.PENDING)
                .title(eventInDto.getTitle())
                .views(0L)
                .build();
    }

    public static EventFullDto toEventFullDto(Event event) {
        return new EventFullDto(event.getId(),
                event.getAnnotation(),
                event.getCategory(),
                event.getConfirmedRequests(),
                event.getCreatedOn(),
                event.getDescription(),
                event.getEventDate(),
                UserMapper.toUserShortDto(event.getInitiator()),
                new EventFullDto.Location(event.getLat(), event.getLon()),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.getRequestModeration(),
                event.getState(),
                event.getTitle(),
                event.getViews());
    }

    public static EventShortDto toEventShortDto(Event event) {
        return new EventShortDto(event.getId(),
                event.getAnnotation(),
                event.getCategory(),
                event.getConfirmedRequests(),
                event.getEventDate(),
                UserMapper.toUserShortDto(event.getInitiator()),
                event.getPaid(),
                event.getTitle(),
                event.getViews());
    }
}
