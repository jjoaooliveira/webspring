package com.webapp.entity;

import java.time.Instant;
import java.util.UUID;

public class Task {
    private UUID id;
    private Title title;
    private Content content;
    private Boolean completed;
    private Instant creationDate;
    private Instant expirationDate;

    public Task(Title title, Content content, Instant expirationDate, Boolean completed) {
        this.title = title;
        this.content = content;
        this.expirationDate = expirationDate;
        this.completed = completed;
    }

    public Task(UUID id, Title title, Content content, Instant creationDate, Instant expirationDate, Boolean completed) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.completed = completed;
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

    public Instant getExpiration() {
        return expirationDate;
    }

    public void setExpiration(Instant newExpiration) {
        this.expirationDate = newExpiration;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = !this.completed;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
