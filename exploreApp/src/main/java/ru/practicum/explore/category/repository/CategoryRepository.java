package ru.practicum.explore.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Collection<Category> findAll(PageRequest pageRequest);
//@Query("SELECT e FROM Event e " +
//        "WHERE e.category = :category ")
//Collection<Event> findEventsByCategory(long category);
}
