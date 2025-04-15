package com.webapp.usecase.exception;

public class FailToCreateTaskException extends RuntimeException {
    public FailToCreateTaskException(String message) {
        super(message);
    }
}
