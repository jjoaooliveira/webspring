package com.webapp.repository.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table
public class AnnotationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID UUID;

    @Column(name = "title", length = 20)
    private String title;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "creation_datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime creation;

    public AnnotationEntity() {}

    public AnnotationEntity(UUID uuid, String title, String content, OffsetDateTime creation) {
        this.UUID = uuid;
        this.title = title;
        this.content = content;
        this.creation = creation;
    }

    public UUID getUUID() {
        return UUID;
    }

    public void setUUID(UUID UUID) {
        this.UUID = UUID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreation(OffsetDateTime creation) {
        this.creation = creation;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public OffsetDateTime getCreation() {
        return creation;
    }
}
