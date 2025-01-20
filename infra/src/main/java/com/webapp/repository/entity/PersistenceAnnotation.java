package com.webapp.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

@Table
public class PersistenceAnnotation {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private String UUID;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "creation_datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime creation;
}
