package ru.practicum.explore.service.category.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explore.exception.CategoriesConflictException;
import ru.practicum.explore.exception.NotFoundException;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.repository.category.CategoryRepository;
import ru.practicum.explore.repository.event.EventRepository;
import ru.practicum.explore.service.category.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Category addCategory(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category newCategory) {
        Category category = getCategoryById(newCategory.getId());
        category.setName(newCategory.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
        if (!eventRepository.findByCategory_Id(categoryId).isEmpty()) {
            throw new CategoriesConflictException("Невозможно удалить категорию. Есть связанные события.");
        }
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category getCategoryById(long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Категория не найдена."));
    }

    @Override
    public Page<Category> getCategories(int from, int size) {
        return categoryRepository.findAll(PageRequest.of(from, size));
    }
}
