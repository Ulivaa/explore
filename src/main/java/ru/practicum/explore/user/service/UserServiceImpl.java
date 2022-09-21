package ru.practicum.explore.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.explore.exception.IncorrectParameterException;
import ru.practicum.explore.exception.UserNotFoundException;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.repository.UserRepository;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        hasParams(user);
        checkValidParams(user);
       return userRepository.save(user);
//        return user;
    }

    @Override
    public void deleteUser(long userId) {
        if (getUserById(userId) != null) {
            userRepository.deleteById(userId);
        } else throw new UserNotFoundException(String.format("Пользователь № %d не найден", userId));
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("Пользователь № %d не найден", userId)));
    }

    private boolean checkValidParams(User user) {
        if (user.getEmail() != null) {
            if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
                throw new IncorrectParameterException("Incorrect parameter \"email\"");
            }
        }
        return true;
    }

    private boolean hasParams(User user) {
        if (user.getEmail() == null) {
            throw new IncorrectParameterException("Incorrect parameter \"email\"");
        }
        if (user.getName() == null) {
            throw new IncorrectParameterException("Incorrect parameter \"email\"");
        }
        return true;
    }
}
