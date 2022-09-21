package ru.practicum.explore.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.user.model.UserDto;
import ru.practicum.explore.user.service.UserMapper;
import ru.practicum.explore.user.service.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(path = "/admin/users")
public class UserAdminController {
    private final UserService userService;

    @Autowired
    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    /*-------------------------------------- USER --------------------------------------*/

    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserDto user) {
        return UserMapper.toUserDto(userService.addUser(UserMapper.toUser(user)));
    }

    @GetMapping
    public Collection<UserDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> UserMapper.toUserDto(user))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

    /*-------------------------------------- EVENT --------------------------------------*/


}
