package ru.practicum.explore.exception;

public class EventDateIsOverdueException extends RuntimeException {
    public EventDateIsOverdueException(String message) {
        super(message);
    }
}

