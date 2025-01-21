package com.webapp.entity.exceptions;

public class EmptyTextException extends RuntimeException {
    public EmptyTextException(String message) {
        super(message);
    }
}
