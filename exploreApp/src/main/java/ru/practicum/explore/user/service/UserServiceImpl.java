package ru.practicum.explore.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explore.exception.IncorrectParameterException;
import ru.practicum.explore.exception.NotFoundException;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    }

    @Override
    public void deleteUser(long userId) {
        if (getUserById(userId) != null) {
            userRepository.deleteById(userId);
        } else throw new NotFoundException(String.format("Пользователь № %d не найден", userId));
    }

    @Override
    public Collection<User> getAllUsers(List<Long> ids, int from, int size) {
        return userRepository.findAllById(ids, PageRequest.of(from, size)).stream().collect(Collectors.toList());
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("Пользователь № %d не найден", userId)));
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
            throw new IncorrectParameterException("Incorrect parameter \"name\"");
        }
        return true;
    }
}
