package com.webapp.entity;

import java.time.LocalDateTime;

public class Annotation {
    private Long id;
    private Title title;
    private Content content;
    private LocalDateTime creationDate;

    //front-end annotation constructor
    public Annotation(Title title, Content content) {
        this.title = title;
        this.content = content;
        this.creationDate = LocalDateTime.now();
    }

    //database annotation constructor
    public Annotation(Long id, Title title, Content content, String creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = LocalDateTime.parse(creationDate);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title.getText();
    }

    public String getContent() {
        return content.getText();
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return content.getText();
    }
}
