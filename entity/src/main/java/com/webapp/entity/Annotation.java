package com.webapp.entity;

import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class Annotation {
    private String id;
    private Title title;
    private Content content;
    private TimeControl timeControl;

    public Annotation(Title title, Content content, TimeControl timeControl) {
        this.title = title;
        this.content = content;
        this.timeControl = timeControl;
    }

    public Annotation(String id, Title title, Content content, TimeControl timeControl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeControl = timeControl;
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
        return timeControl.getCreation();
    }

    public String getCreationDate() {
        return timeControl.getCreationDate();
    }

    public String getCreationTime() {
        return timeControl.getCreationTime();
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
