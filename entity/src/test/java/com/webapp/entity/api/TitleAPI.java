package com.webapp.entity.api;

import com.webapp.entity.Title;

public class TitleAPI {
    private Title title;

    public TitleAPI(String text) {
        this.title = new Title(text);
    }

    public String getText() {
        return title.getText();
    }
}
