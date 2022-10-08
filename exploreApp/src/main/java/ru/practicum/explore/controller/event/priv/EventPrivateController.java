package ru.practicum.explore.controller.event.priv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventInDto;
import ru.practicum.explore.model.event.dto.EventShortDto;
import ru.practicum.explore.mapper.event.EventMapper;
import ru.practicum.explore.service.event.EventService;
import ru.practicum.explore.model.request.dto.RequestDto;
import ru.practicum.explore.mapper.request.RequestMapper;
import ru.practicum.explore.service.request.RequestService;
import ru.practicum.explore.service.user.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@Validated
@RestController
@Slf4j
@RequestMapping(path = "/users/{userId}/events")

public class EventPrivateController {
    private final EventService eventService;
    private final UserService userService;
    private final RequestService requestService;

    public EventPrivateController(EventService eventService, UserService userService, RequestService requestService) {
        this.eventService = eventService;
        this.userService = userService;
        this.requestService = requestService;
    }
    /*-------------------------------------------- USER'S EVENTS --------------------------------------------*/

    @PostMapping
    public EventFullDto addEvent(@PathVariable long userId, @RequestBody @Valid EventInDto eventInDto) {
        return EventMapper.toEventFullDto(eventService.addEvent(EventMapper.toEvent(eventInDto), userId));
    }

    @GetMapping
    public Collection<EventShortDto> getUserEvents(@PathVariable long userId,
                                                   @RequestParam(defaultValue = "0") int from,
                                                   @RequestParam(defaultValue = "10") int size) {
        return eventService.getUserEvents(userId, from, size).stream()
                .map(event -> EventMapper.toEventShortDto(event))
                .collect(Collectors.toList());
    }

    @PatchMapping
    public EventFullDto updateUserEvent(@PathVariable long userId, @RequestBody EventInDto eventInDto) {
        return EventMapper.toEventFullDto(eventService.updateEventByUser(EventMapper.toEvent(eventInDto), userId));

    }

    @GetMapping("/{eventId}")
    public EventFullDto getUserEvent(@PathVariable long userId, @PathVariable long eventId) {
        return EventMapper.toEventFullDto(eventService.getUserEvent(eventId, userId));
    }

    @PatchMapping("/{eventId}")
    public EventFullDto cancelUserEvent(@PathVariable long userId, @PathVariable long eventId) {
        return EventMapper.toEventFullDto(eventService.cancelEvent(userId, eventId));
    }

    /*-------------------------------------------- REQUESTS --------------------------------------------*/

    @GetMapping("/{eventId}/requests")
    public Collection<RequestDto> getUserEventRequests(@PathVariable long userId, @PathVariable long eventId) {
        return requestService.getEventRequests(userId, eventId).stream()
                .map(request -> RequestMapper.toRequestDto(request))
                .collect(Collectors.toList());
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public RequestDto confirmUserEventRequest(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return RequestMapper.toRequestDto(requestService.confirmRequest(userId, eventId, reqId));
    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public RequestDto rejectUserEventRequest(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return RequestMapper.toRequestDto(requestService.rejectRequest(userId, eventId, reqId));
    }
}
