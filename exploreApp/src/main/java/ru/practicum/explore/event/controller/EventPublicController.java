package ru.practicum.explore.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    //    URI uri = URI.create("http://localhost:9090/hit");
    URI uri;
    HttpClient client = HttpClient.newHttpClient();


//    private final EventClient eventClient;
    @Autowired
    public EventPublicController(EventService eventService,
//            , EventClient eventClient
                                 @Value("${statistic.url}") String uri
    ) {
        this.uri = URI.create(uri);
        this.eventService = eventService;
//        this.eventClient = eventClient;
    }

    //TODO сервис статистики
    @GetMapping
    public Collection<EventShortDto> getFilteredEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(required = false) Sort sort,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size, HttpServletRequest request) throws IOException, InterruptedException {


        HttpRequest statRequest = HttpRequest.newBuilder()
                .uri(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .header("Accept", "text/html")
                .header("Content-Type", "application/json")
//                .header("Connection", "keep-alive")
                .POST(HttpRequest.BodyPublishers.ofString(EndpointHit.builder().app("explore").timestamp(LocalDateTime.now()).uri(request.getRequestURI()).ip(request.getRemoteAddr()).build().toString()))
                .build();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

        HttpResponse<String> response = client.send(statRequest, handler);


//        eventClient.saveHit(EndpointHit.builder().app("explore").timestamp(LocalDateTime.now()).uri(request.getRequestURI()).ip(request.getRemoteAddr()).build());

        return eventService.getFilteredEvents(text,
                        categories,
                        paid,
                        rangeStart,
                        rangeEnd,
                        onlyAvailable,
                        sort,
                        from,
                        size).stream()
                .map(event -> EventMapper.toEventShortDto(event))
                .collect(Collectors.toList());
    }

    //TODO сервис статистики
    @GetMapping("/{eventId}")
    public EventShortDto getEventById(@PathVariable long eventId) {
        return EventMapper.toEventShortDto(eventService.getPublishedEventById(eventId));
    }
}
