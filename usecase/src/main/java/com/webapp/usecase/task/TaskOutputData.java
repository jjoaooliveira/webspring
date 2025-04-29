package com.webapp.usecase.task;

import java.util.UUID;

public class TaskOutputData {
    private final UUID id;
    private String title;
    private String content;
    private String creationDate;
    private String expirationDate;
    private boolean completed;

    public TaskOutputData(UUID id, String title, String content, String creationDate, String expirationDate, boolean completed) {
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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
