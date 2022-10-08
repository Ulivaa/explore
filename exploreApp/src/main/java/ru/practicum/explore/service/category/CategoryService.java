package ru.practicum.explore.service.category;

import org.springframework.data.domain.Page;
import ru.practicum.explore.model.category.Category;

public interface CategoryService {
    /**
     * добавить категорию
     */
    Category addCategory(Category category);

    /**
     * обновить категорию
     */
    Category updateCategory(Category category);

    /**
     * удалить категорию по id
     */
    void deleteCategory(long categoryId);

    /**
     * достать категорию по id
     */
    Category getCategoryById(long categoryId);

    /**
     * достать категории постранично
     */
    Page<Category> getCategories(int from, int size);


}
