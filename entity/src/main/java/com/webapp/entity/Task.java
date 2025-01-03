package com.webapp.entity;

import java.time.ZonedDateTime;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Task {
    private final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
    private Long id;
    private Title title;
    private Content content;
    private ZonedDateTime creationDate;
    private ZonedDateTime expirationDate;
    private Boolean completed;
    private Boolean expired;
    private String timeLeft;

    //front-end params task constructor
    public Task(Title title, Content content, LocalDateTime expirationDate) {
        this.title = title;
        this.content = content;
        this.creationDate = ZonedDateTime.now(zoneId);
        this.expirationDate = ZonedDateTime.of(expirationDate, zoneId);
        this.completed = false;
        setExpired();
        setTimeLeft();
    }

    public Task(Long id, Title title, Content content, String creationDate, String expirationDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = ZonedDateTime.parse(creationDate);
        this.expirationDate = ZonedDateTime.parse(expirationDate);
        this.completed = completed;
        setExpired();
        setTimeLeft();
    }

    public Long getId() {
        return this.id;
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

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public ZonedDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(ZonedDateTime expirationDate) {
        //TODO trocar o tipo de parametro e implementar verificacao
        this.expirationDate = expirationDate;
        setTimeLeft();
        setExpired();
    }

    public String getTimeLeft() {
        return timeLeft;
    }
    
    private void setTimeLeft() {
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        Duration duration = Duration.between(now, expirationDate);
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long seconds = duration.toSeconds();

        if (days > 0) {
            this.timeLeft = hours % 24 > 0 
                ? (days + 1) + " dia(s)"
                : days + " dia(s)";
            return;
        } 
        if (hours > 0) {
            this.timeLeft = minutes % 60  > 0
                ? (hours + 1) + " hora(s)"
                : hours + " hora(s)";
            return;
        };
        if(minutes > 0) {
            this.timeLeft = seconds % 60 > 0 
                ? (minutes + 1) + " minuto(s)"
                : minutes + " minuto(s)";
            return;
        }

        this.timeLeft = seconds > 0 
            ? seconds + " segundo(s)"
            : "expirado"; 
    }
    
    public Boolean isExpired() {
        return expired;
    }
    
    private void setExpired() {
        this.expired = ZonedDateTime
            .now(zoneId)
            .isAfter(expirationDate);
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = !this.completed;
    }

    @Override
    public String toString() {
        return getContent() + " - " + getTimeLeft();
    }
}
