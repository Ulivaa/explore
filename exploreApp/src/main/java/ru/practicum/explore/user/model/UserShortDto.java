package ru.practicum.explore.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserShortDto {
    private long id;
    private String name;
}