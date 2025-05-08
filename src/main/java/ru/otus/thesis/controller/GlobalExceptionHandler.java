package ru.otus.thesis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.rest.dto.ResultDto;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResultDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ResultDto(-1, ex.getMessage()));
    }

}
