package ru.practicum.explore.event.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.model.EventFullDto;
import ru.practicum.explore.event.model.EventInDto;
import ru.practicum.explore.event.service.EventMapper;
import ru.practicum.explore.event.service.EventService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(path = "/admin/events")

public class EventAdminController {
    private final EventService eventService;

    public EventAdminController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public Collection<EventFullDto> getEventByAdmin(
            @RequestParam(required = false) Integer[] users,
            @RequestParam(required = false) String[] states,
            @RequestParam(required = false) Integer[] categories,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size) {
        return eventService.getEventByAdmin(users, states, categories, rangeStart, rangeEnd, from, size)
                .stream()
                .map(EventMapper::toEventFullDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{eventId}")
    public EventFullDto updateEventByAdmin(@PathVariable long eventId, @RequestBody EventInDto eventInDto) {
        return EventMapper.toEventFullDto(eventService.updateEventByAdmin(eventId, EventMapper.toEvent(eventInDto)));
    }

    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEventByAdmin(@PathVariable long eventId) {
        return EventMapper.toEventFullDto(eventService.publishEvent(eventId));
    }

    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEventByAdmin(@PathVariable long eventId) {
        return EventMapper.toEventFullDto(eventService.rejectEvent(eventId));
    }
}
