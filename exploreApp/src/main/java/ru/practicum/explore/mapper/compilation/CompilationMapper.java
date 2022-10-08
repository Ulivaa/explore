package ru.practicum.explore.mapper.compilation;

import ru.practicum.explore.model.compilation.Compilation;
import ru.practicum.explore.model.compilation.dto.CompilationInDto;
import ru.practicum.explore.model.compilation.dto.CompilationOutDto;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.mapper.event.EventMapper;

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
