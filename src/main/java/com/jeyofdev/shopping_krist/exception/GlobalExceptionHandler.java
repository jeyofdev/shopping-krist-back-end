package com.jeyofdev.shopping_krist.exception;

import com.jeyofdev.shopping_krist.exception.model.ErrorResponse;
import com.jeyofdev.shopping_krist.util.Helper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * to handle the case when a NotFoundException is thrown
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request, null);
    }

    /**
     * to handle the case when the user is not found.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request, null);
    }

    /**
     * to handle the case when the provided username already exists in the database.
     */
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyTakenException(UsernameAlreadyTakenException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, null);
    }

    /**
     * to handle the case when the provided username or password are incorrect.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, null);
    }

    /**
     * to handle the case when the user does not have the necessary roles
     * to access the requested resource.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.UNAUTHORIZED, request, null);
    }

    /**
     * to handle the case where a method was invoked while the object was not in a suitable state to execute it
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, null);
    }

    /**
     * to handle the case where a method was invoked while the provided token has expired
     */
    @ExceptionHandler(ExpireTokenException.class)
    public ResponseEntity<ErrorResponse> handleExpireTokenException(ExpireTokenException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, null);
    }

    /**
     * to handle the case where a method was invoked while the token is missing or invalid
     */
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, null);
    }

    /**
     * to handle the case where a method was called with one or more arguments that failed validation
     */
    @ExceptionHandler(BadValidationArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadValidationArgumentException(BadValidationArgumentException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, null);
    }

    /**
     * to handle the case where a method was called with one or more invalid arguments
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request) {
        String errorMessage = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList().getFirst();

        return handleException(exception, HttpStatus.BAD_REQUEST, request, errorMessage);
    }

    /**
     * Others
     */
    private ResponseEntity<ErrorResponse> handleException(Exception exception, HttpStatus status, HttpServletRequest request, String message) {
        exception.printStackTrace();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(message != null ? message : exception.getMessage())
                .status(status.value())
                .exceptionName(exception.getClass().getSimpleName())
                .date(Helper.simpleDateFormat())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }
}


