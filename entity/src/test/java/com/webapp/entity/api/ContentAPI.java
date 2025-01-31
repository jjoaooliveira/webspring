package com.webapp.entity.api;

import com.webapp.entity.Content;

public class ContentAPI {
    private Content content;

    public ContentAPI(String text) {
        this.content = new Content(text);
    }

    public String getText() {
        return content.getText();
    }
}
