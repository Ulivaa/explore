package ru.practicum.explore.category.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.repository.CategoryRepository;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.exception.CategoriesConflictException;
import ru.practicum.explore.exception.CategoryNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
//    private final EventService eventService;
    private final EventRepository eventRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
//            , EventService eventService
                               EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
//        this.eventService = eventService;
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
        if (!eventRepository.findByCategory_Id(categoryId).isEmpty()){
            throw new CategoriesConflictException("Невозможно удалить категорию. Есть связанные события.");
        }
         categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category getCategoryById(long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Категория не найдена."));
    }

    @Override
    public Page<Category> getCategories(int from, int size) {
        return categoryRepository.findAll(PageRequest.of(from, size));
    }
}
