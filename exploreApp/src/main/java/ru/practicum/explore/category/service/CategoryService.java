package ru.practicum.explore.category.service;

import org.springframework.data.domain.Page;
import ru.practicum.explore.category.model.Category;

public interface CategoryService {

    Category addCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(long categoryId);

    Category getCategoryById(long categoryId);

    Page<Category> getCategories(int from, int size);


}
