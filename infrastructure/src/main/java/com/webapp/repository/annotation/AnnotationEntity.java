package com.webapp.repository.annotation;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
class AnnotationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID UUID;

    @Column(name = "title", length = 20)
    private String title;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "creation_datetime", updatable = false)
    private Instant creation;

    public AnnotationEntity() {}

    public AnnotationEntity(UUID uuid, String title, String content, Instant creation) {
        this.UUID = uuid;
        this.title = title;
        this.content = content;
        this.creation = creation;
    }

    @PrePersist
    void onCreate() {
        this.creation = Instant.now();
    }

    UUID getUUID() {
        return UUID;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setContent(String content) {
        this.content = content;
    }

    String getTitle() {
        return title;
    }

    String getContent() {
        return content;
    }

    Instant getCreation() {
        return creation;
    }
}
