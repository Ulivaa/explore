package ru.practicum.explore.user.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.request.model.Request;

import java.util.Collection;

@Validated
@RestController
@RequestMapping(path = "/users/{userId}/requests")
public class UserPrivateController {

    @GetMapping
    public Collection<Request> getUserRequests(@PathVariable long userId) {
        return null;
    }

    @PostMapping
    public Collection<Request> addUserRequest(@PathVariable long userId) {
        return null;
    }
    @PatchMapping("/{requestId}/cancel")
    public Collection<Request> cancelUserRequests(@PathVariable long userId, @PathVariable long requestId) {
        return null;
    }
}
