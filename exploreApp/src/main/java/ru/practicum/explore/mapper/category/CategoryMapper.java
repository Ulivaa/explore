package ru.practicum.explore.mapper.category;

import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.category.dto.CategoryDto;

public class CategoryMapper {

    public static CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public static Category toCategory(CategoryDto categoryDto) {
        return Category.builder().id(categoryDto.getId()).name(categoryDto.getName()).build();
    }
}
