package com.webapp.repository.entity;

import jakarta.persistence.*;
import java.util.UUID;

import java.time.OffsetDateTime;

@Entity
@Table
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID UUID;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "creation_datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime creation;

    @Column(name = "expiration_datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime expiration;

    @Column(name = "completed")
    private Boolean completed;

    public TaskEntity(){}

    public TaskEntity(UUID UUID, String title, String content, OffsetDateTime creation, OffsetDateTime expiration, Boolean completed) {
        this.UUID = UUID;
        this.title = title;
        this.content = content;
        this.creation = creation;
        this.expiration = expiration;
        this.completed = completed;
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

    public OffsetDateTime getCreation() {
        return creation;
    }

    public void setCreation(OffsetDateTime creation) {
        this.creation = creation;
    }

    public OffsetDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(OffsetDateTime expiration) {
        this.expiration = expiration;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
