package ru.practicum.explore.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIncorrectParameterException(final IncorrectParameterException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleEventDateIsOverdueException(final EventDateIsOverdueException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleUserNotFoundException(final UserNotFoundException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleCategoryNotFoundException(final CategoryNotFoundException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEventNotFoundException(final EventNotFoundException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleCompilationNotFoundException(final CompilationNotFoundException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleRequestNotFoundException(final RequestNotFoundException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleMemberLimitExceededException(final MemberLimitExceededException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDuplicateRequestException(final DuplicateRequestException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleCategoriesConflictException(final CategoriesConflictException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleUserDoesNotHaveAccessException(final UserDoesNotHaveAccessException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleEventNotHaveStatePendingException(final EventHasInappropriateStateException e) {
        return new ApiError( new String[]{}, e.getMessage(), HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN.value());
    }
}
