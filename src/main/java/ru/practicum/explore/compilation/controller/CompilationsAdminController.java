package ru.practicum.explore.compilation.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.compilation.model.Compilation;

import java.util.Collection;

@RestController
@RequestMapping(path = "/admin/compilations")
public class CompilationsAdminController {

    @PostMapping
    public Compilation addCompilation() {
        return null;
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable long compId) {
    }


    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable long compId, @PathVariable long eventId) {
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilation(@PathVariable long compId, @PathVariable long eventId) {
    }

    @DeleteMapping("/{compId}/pin")
    public void unpinCompilation(@PathVariable long compId) {
    }

    @PatchMapping("/{compId}/pin")
    public void pinCompilation(@PathVariable long compId) {
    }
}
