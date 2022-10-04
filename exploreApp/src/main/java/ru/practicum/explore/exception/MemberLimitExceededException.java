package ru.practicum.explore.exception;

public class MemberLimitExceededException extends RuntimeException {
    public MemberLimitExceededException(String message) {
        super(message);
    }
}