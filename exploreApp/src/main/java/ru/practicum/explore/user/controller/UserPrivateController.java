package ru.practicum.explore.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.request.model.RequestDto;
import ru.practicum.explore.request.service.RequestMapper;
import ru.practicum.explore.request.service.RequestService;
import ru.practicum.explore.user.service.UserService;

import java.util.Collection;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(path = "/users/{userId}")
public class UserPrivateController {

    private final RequestService requestService;
    private final UserService userService;

    @Autowired
    public UserPrivateController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }
    /*-------------------------------------------- USER'S REQUESTS --------------------------------------------*/

    @GetMapping("/requests")
    public Collection<RequestDto> getUserRequests(@PathVariable long userId) {
        return requestService.getUserRequests(userId).stream()
                .map(request -> RequestMapper.toRequestDto(request))
                .collect(Collectors.toList());
    }

    @PostMapping("/requests")
    public RequestDto addUserRequest(@PathVariable long userId, @RequestParam long eventId) {
        return RequestMapper.toRequestDto(requestService.addRequest(RequestMapper.toRequest(userId, eventId)));
    }

    @PatchMapping("/requests/{requestId}/cancel")
    public RequestDto cancelUserRequest(@PathVariable long userId, @PathVariable long requestId) {
        return RequestMapper.toRequestDto(requestService.cancelRequest(userId, requestId));
    }

    /*-------------------------------------------- USER'S CATEGORY --------------------------------------------*/

    /*-------------------------------------------- ФИЧА --------------------------------------------*/

    @PostMapping("/categories/{catId}")
    public void addFavoriteCategory(@PathVariable long userId, @PathVariable long catId) {
        userService.addFavoriteCategory(userId, catId);
    }

    @DeleteMapping("/categories/{catId}")
    public void deleteFavoriteCategory(@PathVariable long userId, @PathVariable long catId) {
        userService.deleteFavoriteCategory(userId, catId);
    }

    @GetMapping("/categories")
    public Collection<Category> getFavoriteCategories(@PathVariable long userId) {
        return userService.getFavoriteCategories(userId);
    }
    /*-------------------------------------------- ФИЧА --------------------------------------------*/

}
