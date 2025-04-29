package com.webapp.usecase.task;

import java.util.UUID;

public class TaskOutputData {
    private final UUID uuid;
    private String title;
    private String content;
    private String creation;
    private String expiration;
    private boolean completed;

    public TaskOutputData(UUID uuid, String title, String content, String creation, String expiration, boolean completed) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        this.creation = creation;
        this.expiration = expiration;
        this.completed = completed;
    }

    public UUID getUuid() {
        return uuid;
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

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
