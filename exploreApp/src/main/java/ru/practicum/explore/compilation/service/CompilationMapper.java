package ru.practicum.explore.compilation.service;

import ru.practicum.explore.compilation.model.Compilation;
import ru.practicum.explore.compilation.model.CompilationInDto;
import ru.practicum.explore.compilation.model.CompilationOutDto;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.service.EventMapper;

import java.util.stream.Collectors;

public class CompilationMapper {
    public static Compilation toCompilation(CompilationInDto compilationInDto) {
        return Compilation.builder()
                .events(compilationInDto.getEvents().stream().map(i -> Event.builder().id(i).build()).collect(Collectors.toSet()))
                .pinned(compilationInDto.getPinned() == null ? false : compilationInDto.getPinned())
                .title(compilationInDto.getTitle())
                .build();

    }

    public static CompilationOutDto toCompilationOutDto(Compilation compilation) {
        return new CompilationOutDto(compilation.getId(),
                compilation.getPinned(),
                compilation.getTitle(),
                compilation.getEvents().stream().map(event -> EventMapper.toEventShortDto(event)).collect(Collectors.toSet()));
    }
}
