package com.webapp.entity.exceptions;

public class IllegalExpirationDateException extends RuntimeException {
    public IllegalExpirationDateException(String message) {
        super(message);
    }
}
