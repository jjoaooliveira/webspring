package com.webapp.entity;

import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class Task {
    private String id;
    private Title title;
    private Content content;
    private Boolean completed;
    private TimedControl timedControl;
    private boolean expired;

    public Task(Title title, Content content, TimedControl timedControl) {
        this.title = title;
        this.content = content;
        this.timedControl = timedControl;
        this.completed = false;
        setExpired();
    }

    public Task(String id, Title title, Content content, TimedControl timedControl, boolean completed) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timedControl = timedControl;
        this.completed = completed;
        setExpired();
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

    public String getCreationDate() {
        return timedControl.getCreationDate();
    }

    public String getExpirationDate() {
        return timedControl.getExpirationDate();
    }

    public void setExpiration(String newExpiration, String zone) {
        timedControl.setExpiration(newExpiration, zone);
    }

    public String getTimeLeft() {
        return timedControl.getTimeLeft();
    }

    public Boolean isExpired() {
        return expired;
    }

    private void setExpired() {
        this.expired = timedControl.isExpired();
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = !this.completed;
    }

    @Override
    public String toString() {
        return getTitle() + " - " + getTimeLeft();
    }
}
