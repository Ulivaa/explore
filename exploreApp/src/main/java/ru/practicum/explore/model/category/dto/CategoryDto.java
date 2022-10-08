package ru.practicum.explore.model.category.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryDto {
    public Long id;
    @NotNull
    private String name;
}
