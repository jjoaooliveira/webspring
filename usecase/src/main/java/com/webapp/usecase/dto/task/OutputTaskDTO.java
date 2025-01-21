package com.webapp.usecase.dto.task;

import java.time.OffsetDateTime;
import java.util.UUID;

public record OutputTaskDTO(
        UUID id,
        String title,
        String content,
        OffsetDateTime creation,
        OffsetDateTime expiration,
        String timeLeft,
        boolean completed,
        boolean expired
        ) {}
