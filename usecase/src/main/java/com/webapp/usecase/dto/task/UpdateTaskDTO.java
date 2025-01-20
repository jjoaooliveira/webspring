package com.webapp.usecase.dto.task;

import java.time.OffsetDateTime;

public record UpdateTaskDTO(
        String id,
        String title,
        String content,
        OffsetDateTime expirationDate,
        Boolean completed
) {
}
