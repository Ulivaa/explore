package ru.practicum.explore.category.service;

import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.model.CategoryDto;

public class CategoryMapper {

    public static CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public static Category  toCategory(CategoryDto categoryDto) {
        return Category.builder().id(categoryDto.getId()).name(categoryDto.getName()).build();
    }
}
