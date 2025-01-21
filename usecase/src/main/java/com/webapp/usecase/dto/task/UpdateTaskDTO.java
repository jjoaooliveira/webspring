package com.webapp.usecase.dto.task;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UpdateTaskDTO(
        UUID id,
        String title,
        String content,
        OffsetDateTime expirationDate,
        Boolean completed
) {
}
