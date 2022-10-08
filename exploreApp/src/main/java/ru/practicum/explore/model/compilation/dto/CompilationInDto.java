package ru.practicum.explore.model.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CompilationInDto {
    private Long id;
    private Boolean pinned;
    @NotNull
    private String title;
    private Set<Long> events;

}
