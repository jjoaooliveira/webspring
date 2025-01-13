package com.webapp.entity;

public class Task {
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

    public Task(Title title, Content content, TimedControl timedControl, boolean completed) {
        this.title = title;
        this.content = content;
        this.timedControl = timedControl;
        this.completed = completed;
        setExpired();
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getContent() {
        return content.getText();
    }

    public void setContent(Content content) {
        this.content = content;
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
