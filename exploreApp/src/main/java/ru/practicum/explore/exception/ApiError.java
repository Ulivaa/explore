package ru.practicum.explore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiError {
    private final String[] errors;
    private final String message;

    private final String reason;

    private final String status;

    private final LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(String[] errors, String message, String reason, int status) {
        this.errors = errors;
        this.message = message;
        this.reason = reason;
        this.status = "" + status;
    }
}
