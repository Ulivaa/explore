package ru.practicum.explore.event.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.model.EventFullDto;
import ru.practicum.explore.event.service.EventService;

import java.util.Collection;

@Validated
@RestController
@RequestMapping(path = "/admin/events")

public class EventAdminController {
    private final EventService eventService;

    public EventAdminController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public Collection<EventFullDto> getEventByAdmin() {
        return null;
    }

    @PutMapping("/{eventId}")
    public EventFullDto updateEventByAdmin(@PathVariable String eventId) {
        return null;
    }

    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEventByAdmin(@PathVariable String eventId) {
        return null;
    }

    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEventByAdmin(@PathVariable String eventId) {
        return null;
    }
}
