package ru.practicum.explore.service.user;

import ru.practicum.explore.model.user.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    /**
     * добавить пользователя
     */
    User addUser(User user);

    /**
     * удалить пользователя
     */
    void deleteUser(long userId);

    /**
     * достать пользователя
     */
    User getUserById(long userId);

    /**
     * достать всех пользователей по параметру постранично
     */
    Collection<User> getAllUsers(List<Long> ids, int from, int size);
}
