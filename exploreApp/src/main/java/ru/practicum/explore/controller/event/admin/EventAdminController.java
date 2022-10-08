package ru.practicum.explore.controller.event.admin;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventInDto;
import ru.practicum.explore.mapper.event.EventMapper;
import ru.practicum.explore.service.event.EventService;

import java.util.Collection;
import java.util.List;
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
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<String> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
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
