package com.webapp.entity;

import java.time.ZonedDateTime;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Task {
    private final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
    private Content content;
    private ZonedDateTime creationDate;
    private ZonedDateTime expirationDate;
    private Boolean completed;
    private Boolean expired;
    private String timeLeft;

    public Task(Content content, LocalDateTime expirationDate) {
        this.content = content;
        this.creationDate = ZonedDateTime.now(zoneId);
        this.expirationDate = ZonedDateTime.of(expirationDate, zoneId);
        this.completed = false;
        setExpired();
        setTimeLeft();
    }

    public Task(Content content, String creationDate, String expirationDate, boolean completed) {
        this.content = content;
        this.creationDate = ZonedDateTime.parse(creationDate);
        this.expirationDate = ZonedDateTime.parse(expirationDate);
        this.completed = completed;
        setExpired();
        setTimeLeft();
    }

    public Content getContent() {
        return content;
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
        return getContent().getText() + " - " + getTimeLeft();
    }
}
