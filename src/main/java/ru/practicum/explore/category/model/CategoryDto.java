package ru.practicum.explore.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
    private long id;
    @NotNull
    private String name;
}
