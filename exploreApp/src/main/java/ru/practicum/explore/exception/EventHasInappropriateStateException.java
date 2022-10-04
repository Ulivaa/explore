package ru.practicum.explore.exception;

public class EventHasInappropriateStateException extends RuntimeException {

    public EventHasInappropriateStateException(String message) {
        super(message);
    }
}

