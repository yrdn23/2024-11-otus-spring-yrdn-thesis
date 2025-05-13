package ru.otus.thesis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.exceptions.InconsistentDataException;
import ru.otus.thesis.rest.dto.ResultDto;

import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResultDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ResultDto(-1, ex.getMessage()));
    }

    @ExceptionHandler(InconsistentDataException.class)
    public ResponseEntity<ResultDto> handleInconsistentDataException(InconsistentDataException ex) {
        return ResponseEntity.badRequest()
                .body(new ResultDto(-2, ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDto> handleHttpMessageNotReadableExceptions() {
        return ResponseEntity.badRequest()
                .body(new ResultDto(-3, "Incorrect request body"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest()
                .body(new ResultDto(-4, errors));
    }
}
