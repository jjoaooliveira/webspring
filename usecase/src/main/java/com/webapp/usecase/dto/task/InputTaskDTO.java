package com.webapp.usecase.dto.task;

import java.time.OffsetDateTime;
import java.util.UUID;

public record InputTaskDTO(
        UUID id,
        String title,
        String content,
        OffsetDateTime creationDate,
        OffsetDateTime expirationDate,
        Boolean completed
) {}
