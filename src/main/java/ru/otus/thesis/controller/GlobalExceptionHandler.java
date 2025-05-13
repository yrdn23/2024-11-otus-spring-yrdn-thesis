package ru.otus.thesis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.exceptions.InconsistentDataException;
import ru.otus.thesis.rest.dto.ResultResponse;

import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResultResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ResultResponse(-1, ex.getMessage()));
    }

    @ExceptionHandler(InconsistentDataException.class)
    public ResponseEntity<ResultResponse> handleInconsistentDataException(InconsistentDataException ex) {
        return ResponseEntity.badRequest()
                .body(new ResultResponse(-2, ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultResponse> handleHttpMessageNotReadableExceptions() {
        return ResponseEntity.badRequest()
                .body(new ResultResponse(-3, "Incorrect request body"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest()
                .body(new ResultResponse(-4, errors));
    }
}
