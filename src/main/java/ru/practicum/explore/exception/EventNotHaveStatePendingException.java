package ru.practicum.explore.exception;

public class EventNotHaveStatePendingException extends RuntimeException {
    private final String parameter;

    public EventNotHaveStatePendingException(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}