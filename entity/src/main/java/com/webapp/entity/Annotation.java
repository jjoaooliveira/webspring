package com.webapp.entity;

import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class Annotation {
    private String id;
    private Title title;
    private Content content;
    private TimeMark timeMark;

    public Annotation(Title title, Content content, TimeMark timeMark) {
        this.title = title;
        this.content = content;
        this.timeMark = timeMark;
    }

    public Annotation(String id, Title title, Content content, TimeMark timeMark) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeMark = timeMark;
    }

    public String getId() {
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

    public void setContent(String content) throws TextLengthOverLimitException, EmptyTextException {
        this.content = new Content(content);
    }

    public String getCreation() {
        return timeMark.getCreation();
    }

    public String getCreationDate() {
        return timeMark.getCreationDate();
    }

    public String getCreationTime() {
        return timeMark.getCreationTime();
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
