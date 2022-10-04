package ru.practicum.explore.exception;

public class UserDoesNotHaveAccessException extends RuntimeException {
    public UserDoesNotHaveAccessException(String message) {
        super(message);
    }
}
