package com.webapp.entity.exceptions;

public class TextLengthOverLimitException extends RuntimeException {
    public TextLengthOverLimitException(String message) {
        super(message);
    }
}
