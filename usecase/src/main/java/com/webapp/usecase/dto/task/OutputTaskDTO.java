package com.webapp.usecase.dto.task;

import java.time.OffsetDateTime;

public record OutputTaskDTO(
        String id,
        String title,
        String content,
        OffsetDateTime creation,
        OffsetDateTime expiration,
        String timeLeft,
        boolean completed,
        boolean expired
        ) {}
