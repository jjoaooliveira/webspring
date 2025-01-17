package com.webapp.usecase.dto.task;

public record OutputTaskDTO(
        String id,
        String title,
        String content,
        String creationDate,
        String creationTime,
        String expirationDate,
        String expirationTime,
        String timeLeft,
        boolean completed,
        boolean expired
        ) {}
