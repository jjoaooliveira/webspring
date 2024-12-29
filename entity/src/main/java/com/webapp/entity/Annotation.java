package com.webapp.entity;

import java.time.ZonedDateTime;

public class Annotation {
    private Content content;
    private ZonedDateTime creationDate;

    public Annotation(Content content) {
        this.content = content;
        this.creationDate = ZonedDateTime.now();
    }

    public Annotation(Content content, String creationDate) {
        this.content = content;
        this.creationDate = ZonedDateTime.parse(creationDate);
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return content.getText();
    }
}
