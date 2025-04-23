package com.webapp.entity;

public class Title {
    private final String title;

    public Title(String text) {
        this.title = text == null || text.isBlank()
            ? "Insira um titulo..."
            : text;
    }

    public String getText() {
        return title;
    }
}
