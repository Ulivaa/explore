package ru.practicum.explore.mapper.user;

import ru.practicum.explore.model.user.User;
import ru.practicum.explore.model.user.dto.UserDto;
import ru.practicum.explore.model.user.dto.UserShortDto;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail());
    }

    public static UserShortDto toUserShortDto(User user) {
        return new UserShortDto(user.getId(),
                user.getName());
    }

    public static User toUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }
}
