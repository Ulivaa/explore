package ru.practicum.explore.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.model.EventFullDto;
import ru.practicum.explore.event.model.EventInDto;
import ru.practicum.explore.event.model.EventShortDto;
import ru.practicum.explore.event.service.EventMapper;
import ru.practicum.explore.event.service.EventService;
import ru.practicum.explore.user.service.UserService;

import javax.validation.Valid;
import java.util.Collection;

@Validated
@RestController
@Slf4j
@RequestMapping(path = "/users/{userId}/events")

public class EventPrivateController {
    private final EventService eventService;
    private final UserService userService;

    public EventPrivateController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    /*-------------------------------------------- USER'S EVENTS --------------------------------------------*/

    @PostMapping
    public EventFullDto addEvent(@PathVariable long userId, @RequestBody @Valid EventInDto eventInDto) {
//        log.info("________________ ПОПАЛИ ____________________-");
        return EventMapper.toEventFullDto(eventService.addEvent(EventMapper.toEvent(eventInDto), userId));
    }

    @GetMapping
    public Collection<EventShortDto> getUserEvents(@PathVariable long userId) {
        return null;
    }

    @PatchMapping
    public EventFullDto updateUserEvent(@PathVariable long userId, @RequestBody EventInDto eventInDto) {
        return EventMapper.toEventFullDto(eventService.updateEvent(EventMapper.toEvent(eventInDto), userId));

    }

    @GetMapping("/{eventId}")
    public EventFullDto getUserEvent(@PathVariable long userId, @PathVariable long eventId) {
        return null;
    }

    @PatchMapping("/{eventId}")
    public EventFullDto cancelUserEvent(@PathVariable long userId, @PathVariable long eventId) {
        return null;
    }

    /*-------------------------------------------- REQUESTS --------------------------------------------*/

    @GetMapping("/{eventId}/requests")
    public EventFullDto getUserEventRequests(@PathVariable long userId, @PathVariable long eventId) {
        return null;
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public EventFullDto confirmUserEventRequest(@PathVariable long userId, @PathVariable long eventId, @PathVariable String reqId) {
        return null;
    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public EventFullDto rejectUserEventRequest(@PathVariable long userId, @PathVariable long eventId, @PathVariable String reqId) {
        return null;
    }
}
