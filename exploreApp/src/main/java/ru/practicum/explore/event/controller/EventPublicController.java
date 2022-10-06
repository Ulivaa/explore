package ru.practicum.explore.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.ConfigProperties;
import ru.practicum.explore.event.model.EventShortDto;
import ru.practicum.explore.event.model.Sort;
import ru.practicum.explore.event.service.EventMapper;
import ru.practicum.explore.event.service.EventService;
import ru.practicum.explore.statistic.EndpointHit;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/events")

public class EventPublicController {
    private final EventService eventService;
    URI uri;
    HttpClient client = HttpClient.newHttpClient();

    @Autowired
    public EventPublicController(EventService eventService,
                                 ConfigProperties configProperties) {
        this.uri = URI.create(configProperties.getStatistic() + "/hit");
        this.eventService = eventService;
    }

    @GetMapping
    public Collection<EventShortDto> getFilteredEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(required = false) Sort sort,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size, HttpServletRequest request) {
        sendStatistic(request);
        return eventService.getFilteredEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size)
                .stream()
                .map(event -> EventMapper.toEventShortDto(event))
                .collect(Collectors.toList());
    }

    @GetMapping("/{eventId}")
    public EventShortDto getEventById(@PathVariable long eventId, HttpServletRequest request) {
        sendStatistic(request);
        return EventMapper.toEventShortDto(eventService.getPublishedEventById(eventId));
    }

    private void sendStatistic(HttpServletRequest request) {
        try {
            HttpRequest statRequest = HttpRequest.newBuilder()
                    .uri(uri)
                    .version(HttpClient.Version.HTTP_1_1)
                    .header("Accept", "text/html")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(EndpointHit.builder().app("explore").timestamp(LocalDateTime.now()).uri(request.getRequestURI()).ip(request.getRemoteAddr()).build().toString()))
                    .build();
            HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
            client.send(statRequest, handler);
        } catch (IOException | InterruptedException ex) {
            log.error("Соединение с сервером статистики потеряно. Невозможно отправить данные.");
        }
    }

}
