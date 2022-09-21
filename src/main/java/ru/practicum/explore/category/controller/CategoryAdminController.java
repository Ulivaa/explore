package ru.practicum.explore.category.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.model.CategoryDto;
import ru.practicum.explore.category.service.CategoryMapper;
import ru.practicum.explore.category.service.CategoryService;

@Validated
@RestController
@RequestMapping(path = "/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;

    public CategoryAdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PatchMapping
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        return null;
    }

    @PostMapping
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        return CategoryMapper.toCategoryDto(categoryService.addCategory(CategoryMapper.toCategory(categoryDto)));
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable String catId) {
    }

}


