package ru.practicum.explore.category.service;

import ru.practicum.explore.category.model.Category;

import java.util.Collection;

public interface CategoryService {

    Category addCategory(Category category);

    Category updateCategory(Category category);

    Category deleteCategory(long categoryId);

    Category getCategoryById(long categoryId);

    Collection<Category> getCategories();


}
