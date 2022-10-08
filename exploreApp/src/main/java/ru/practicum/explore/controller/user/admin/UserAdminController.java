package ru.practicum.explore.controller.user.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.model.user.dto.UserDto;
import ru.practicum.explore.mapper.user.UserMapper;
import ru.practicum.explore.service.user.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
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

    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserDto user) {
        return UserMapper.toUserDto(userService.addUser(UserMapper.toUser(user)));
    }

    @GetMapping
    public Collection<UserDto> getAllUsers(@RequestParam(required = false) List<Long> ids,
                                           @RequestParam(defaultValue = "0") int from,
                                           @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(ids, from, size)
                .stream()
                .map(user -> UserMapper.toUserDto(user))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

}
