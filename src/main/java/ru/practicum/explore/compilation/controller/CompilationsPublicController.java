package ru.practicum.explore.compilation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.compilation.model.Compilation;

import java.util.Collection;

@RestController
@RequestMapping(path = "/compilations")
public class CompilationsPublicController {
    @GetMapping
    public Collection<Compilation> getCompilations() {
        return null;
    }

    @GetMapping("/{compId}")
    public Compilation getCompilationById(@PathVariable long compId) {
        return null;
    }
}
