package ru.practicum.explore.category.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.service.CategoryService;

import java.util.Collection;

@Validated
@RestController
@RequestMapping(path = "/categories")
public class CategoryPublicController {

    private final CategoryService categoryService;

    public CategoryPublicController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Collection<Category> getCategories(@RequestParam(defaultValue = "0") int from,
                                              @RequestParam(defaultValue = "10") int size) {
        return categoryService.getCategories(from, size).toList();
    }

    @GetMapping("/{catId}")
    public Category getCategoryById(@PathVariable long catId) {
        return categoryService.getCategoryById(catId);
    }
}
