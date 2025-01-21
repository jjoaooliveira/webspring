package com.webapp.entity;

import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class Content {
    private String text;

    /**
     * @param text the content text
     * @throws EmptyTextException if the text is empty
     * @throws TextLengthOverLimitException if the text length is over to 100 characters
     * */
    public Content(String text) {
        if (text.isEmpty()) {
            throw new EmptyTextException("The content cannot be empty");
        }
        if (text.length() > 100) {
            throw new TextLengthOverLimitException("The content length must have up to 100 characters");
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return getText();
    }
}
