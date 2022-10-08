package ru.practicum.explore.controller.compilation.pub;

import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.model.compilation.dto.CompilationOutDto;
import ru.practicum.explore.mapper.compilation.CompilationMapper;
import ru.practicum.explore.service.compilation.CompilationService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/compilations")
public class CompilationsPublicController {
    private final CompilationService compilationService;

    public CompilationsPublicController(CompilationService compilationService) {
        this.compilationService = compilationService;
    }

    @GetMapping
    public Collection<CompilationOutDto> getCompilations(@RequestParam(defaultValue = "false") boolean pinned,
                                                         @RequestParam(defaultValue = "0") int from,
                                                         @RequestParam(defaultValue = "10") int size) {
        return compilationService.getCompilations(pinned, from, size)
                .stream()
                .map(compilation -> CompilationMapper.toCompilationOutDto(compilation))
                .collect(Collectors.toList());
    }

    @GetMapping("/{compId}")
    public CompilationOutDto getCompilationById(@PathVariable long compId) {
        return CompilationMapper.toCompilationOutDto(compilationService.getCompilationById(compId));
    }
}
