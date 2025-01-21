package com.webapp.entity;

import java.time.*;
import java.util.UUID;

public class Task {
    private UUID id;
    private Title title;
    private Content content;
    private Boolean completed;
    private TimeMark creationMark;
    private TimedMark expirationMark;
    private boolean expired;

    public Task(Title title, Content content, TimeMark creationMark, TimedMark expirationMark) {
        this.title = title;
        this.content = content;
        this.creationMark = creationMark;
        this.expirationMark = expirationMark;
        this.completed = false;
        setExpired();
    }

    public Task(UUID id, Title title, Content content, TimeMark creationMark, TimedMark expirationMark, boolean completed) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationMark = creationMark;
        this.expirationMark = expirationMark;
        this.completed = completed;
        setExpired();
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

    public OffsetDateTime getCreation() {
        return creationMark.getCreation();
    }

    public LocalDate getCreationDate() {
        return creationMark.getCreationDate();
    }

    public LocalTime getCreationTime() {
        return creationMark.getCreationTime();
    }

    public OffsetDateTime getExpiration() {
        return expirationMark.getExpiration();
    }

    public LocalDate getExpirationDate() {
        return expirationMark.getExpirationDate();
    }

    public LocalTime getExpirationTime() {
        return expirationMark.getExpirationTime();
    }

    public void setExpiration(ZonedDateTime zonedDateTime) {
        this.expirationMark = new TimedMark(zonedDateTime);
    }

    public String getTimeLeft() {
        return expirationMark.getTimeLeft();
    }

    public Boolean isExpired() {
        return expired;
    }

    private void setExpired() {
        this.expired = expirationMark.isExpired();
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
