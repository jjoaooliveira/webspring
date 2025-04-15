package com.webapp.entity;

import java.time.Instant;
import java.util.UUID;

public class Annotation {
    private UUID id;
    private Title title;
    private Content content;
    private final Instant creationDate;

    public Annotation(Title title, Content content, Instant creationDate) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public Annotation(UUID id, Title title, Content content, Instant creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title = new Title(title);
    }

    public String getContent() {
        return content.getText();
    }

    public void setContent(String content) {
        this.content = new Content(content);
    }

    public Instant getCreation() {
        return creationDate;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
