package com.webapp.entity.exceptions;

public class TextLengthOverLimitException extends Exception {
    public TextLengthOverLimitException(String message) {
        super(message);
    }
}
