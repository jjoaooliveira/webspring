package com.webapp.entity;

import com.webapp.entity.exceptions.TextLengthOverLimitException;

import java.util.Objects;

public class Content {
    private final String text;

    /**
     * @param text the content text
     * @throws IllegalArgumentException if the text is empty or null
     * @throws TextLengthOverLimitException if the text length is over to 100 characters
     * */
    public Content(String text) {
        Objects.requireNonNull(text);
        if (text.isBlank()) {
            throw new IllegalArgumentException("The content cannot be empty");
        }
        if (text.length() > 100) {
            throw new TextLengthOverLimitException("The content length must have up to 100 characters");
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
