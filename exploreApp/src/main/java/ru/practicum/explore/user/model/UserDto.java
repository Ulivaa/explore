package ru.practicum.explore.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private long id;
    private String name;
    @Email
    private String email;
}
