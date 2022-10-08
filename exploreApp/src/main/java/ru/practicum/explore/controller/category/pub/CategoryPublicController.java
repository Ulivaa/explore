package ru.practicum.explore.controller.category.pub;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.service.category.CategoryService;

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
