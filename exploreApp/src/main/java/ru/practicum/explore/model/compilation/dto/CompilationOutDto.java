package ru.practicum.explore.model.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.practicum.explore.model.event.dto.EventShortDto;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CompilationOutDto {
    private Long id;
    private Boolean pinned;
    private String title;
    private Set<EventShortDto> events;
}
