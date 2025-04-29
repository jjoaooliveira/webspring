package com.webapp.usecase.annotation;

import java.util.UUID;

public class AnnotationOutputData {
    private final UUID id;
    private String title;
    private String content;
    private String creationDate;

    public AnnotationOutputData(UUID id, String title, String content, String creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
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
}
