package ru.practicum.explore.controller.user.priv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.model.request.dto.RequestDto;
import ru.practicum.explore.mapper.request.RequestMapper;
import ru.practicum.explore.service.request.RequestService;

import java.util.Collection;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(path = "/users/{userId}/requests")
public class UserPrivateController {

    private final RequestService requestService;

    @Autowired
    public UserPrivateController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public Collection<RequestDto> getUserRequests(@PathVariable long userId) {
        return requestService.getUserRequests(userId).stream()
                .map(request -> RequestMapper.toRequestDto(request))
                .collect(Collectors.toList());
    }

    @PostMapping
    public RequestDto addUserRequest(@PathVariable long userId, @RequestParam long eventId) {
        return RequestMapper.toRequestDto(requestService.addRequest(RequestMapper.toRequest(userId, eventId)));
    }

    @PatchMapping("/{requestId}/cancel")
    public RequestDto cancelUserRequest(@PathVariable long userId, @PathVariable long requestId) {
        return RequestMapper.toRequestDto(requestService.cancelRequest(userId, requestId));
    }
}
