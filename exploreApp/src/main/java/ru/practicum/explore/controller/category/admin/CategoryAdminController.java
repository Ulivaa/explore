package ru.practicum.explore.controller.category.admin;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.model.category.dto.CategoryDto;
import ru.practicum.explore.mapper.category.CategoryMapper;
import ru.practicum.explore.service.category.CategoryService;

@Validated
@RestController
@RequestMapping(path = "/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;

    public CategoryAdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PatchMapping
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
        return CategoryMapper.toCategoryDto(categoryService.updateCategory(CategoryMapper.toCategory(categoryDto)));
    }

    @PostMapping
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        return CategoryMapper.toCategoryDto(categoryService.addCategory(CategoryMapper.toCategory(categoryDto)));
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable long catId) {
        categoryService.deleteCategory(catId);
    }

}


