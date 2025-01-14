package com.webapp.entity;

public class Annotation {
    private Title title;
    private Content content;
    private TimeControl timeControl;

    public Annotation(Title title, Content content, TimeControl timeControl) {
        this.title = title;
        this.content = content;
        this.timeControl = timeControl;
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

    public String getCreationDate() {
        return timeControl.getCreationDate();
    }

    public String getCreationTime() {
        return timeControl.getCreationTime();
    }

    public String getCreation() {
        return timeControl.getCreation();
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
