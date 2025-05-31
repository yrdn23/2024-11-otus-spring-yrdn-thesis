package ru.otus.thesis.exceptions;

public class InconsistentRequestException extends RuntimeException {
    public InconsistentRequestException(String message) {
        super(message);
    }
}
