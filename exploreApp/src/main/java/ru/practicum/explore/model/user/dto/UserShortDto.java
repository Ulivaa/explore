package ru.practicum.explore.model.user.dto;

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
