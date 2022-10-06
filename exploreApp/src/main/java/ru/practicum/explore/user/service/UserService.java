package ru.practicum.explore.user.service;

import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.user.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User addUser(User user);

    void deleteUser(long userId);

    User getUserById(long userId);

    Collection<User> getAllUsers(List<Long> ids, int from, int size);

    void addFavoriteCategory(long userId, long catId);

    void deleteFavoriteCategory(long userId, long catId);

    Collection<Category> getFavoriteCategories(long userId);
}
