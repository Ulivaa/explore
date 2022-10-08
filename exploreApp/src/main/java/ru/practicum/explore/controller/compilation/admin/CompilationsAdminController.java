package ru.practicum.explore.controller.compilation.admin;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.model.compilation.dto.CompilationInDto;
import ru.practicum.explore.model.compilation.dto.CompilationOutDto;
import ru.practicum.explore.mapper.compilation.CompilationMapper;
import ru.practicum.explore.service.compilation.CompilationService;

@RestController
@RequestMapping(path = "/admin/compilations")
public class CompilationsAdminController {
    private final CompilationService compilationService;

    public CompilationsAdminController(CompilationService compilationService) {
        this.compilationService = compilationService;
    }

    @PostMapping
    public CompilationOutDto addCompilation(@RequestBody @Validated CompilationInDto compilationInDto) {
        return CompilationMapper.toCompilationOutDto(compilationService.addCompilation(CompilationMapper.toCompilation(compilationInDto)));
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable long compId) {
        compilationService.deleteCompilation(compId);
    }


    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable long compId, @PathVariable long eventId) {
        compilationService.deleteEventFromCompilation(eventId, compId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilation(@PathVariable long compId, @PathVariable long eventId) {
        compilationService.addEventToCompilation(eventId, compId);

    }

    @DeleteMapping("/{compId}/pin")
    public void unpinCompilation(@PathVariable long compId) {
        compilationService.isPinCompilation(compId, false);
    }

    @PatchMapping("/{compId}/pin")
    public void pinCompilation(@PathVariable long compId) {
        compilationService.isPinCompilation(compId, true);
    }
}
