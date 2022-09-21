package ru.practicum.explore.category.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Collection<Category> getCategories() {
        return null;
    }

    @GetMapping("/{catId}")
    public Collection<Category> getCategoryById(@PathVariable String catId) {
        return null;
    }
}
