package ru.practicum.explore.event.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore.event.model.EventShortDto;
import ru.practicum.explore.event.service.EventService;

import java.util.Collection;

@Validated
@RestController
@RequestMapping(path = "/events")

public class EventPublicController {
    private final EventService eventService;

    public EventPublicController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping
    public Collection<EventShortDto> getFilteredEvents(){
        return null;
    }

    @GetMapping("/{eventId}")
    public Collection<EventShortDto> getEventById(@PathVariable long eventId){
        return null;
    }
}
