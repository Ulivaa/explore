package ru.practicum.explore.category.service;

import org.springframework.stereotype.Service;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.repository.CategoryRepository;
import ru.practicum.explore.exception.CategoryNotFoundException;

import java.util.Collection;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public Category deleteCategory(long categoryId) {
        return null;
    }

    @Override
    public Category getCategoryById(long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Категория не найдена."));
    }

    @Override
    public Collection<Category> getCategories() {
        return null;
    }
}
