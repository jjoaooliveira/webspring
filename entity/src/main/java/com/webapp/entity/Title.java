package com.webapp.entity;

public class Title {
    private String title;

    public Title(String text) {
        this.title = text.isEmpty()
            ? "Insira um titulo..."
            : text;
    }

    public String getText() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
