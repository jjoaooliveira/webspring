package com.webapp.repository.task;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID UUID;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "creation_datetime", nullable = false)
    private Instant creation;

    @Column(name = "expiration_datetime", nullable = false)
    private Instant expiration;

    @Column(name = "completed", nullable = false)
    private Boolean completed;

    public TaskEntity(){}

    public TaskEntity(UUID UUID, String title, String content, Instant creation, Instant expiration, Boolean completed) {
        this.UUID = UUID;
        this.title = title;
        this.content = content;
        this.creation = creation;
        this.expiration = expiration;
        this.completed = completed;
    }

    @PrePersist
    void onCreate() {
        this.creation = Instant.now();
    }

    public UUID getUUID() {
        return UUID;
    }

    public void setUUID(UUID UUID) {
        this.UUID = UUID;
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

    public Instant getCreation() {
        return creation;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant newExpiration) {
        this.expiration = newExpiration;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
