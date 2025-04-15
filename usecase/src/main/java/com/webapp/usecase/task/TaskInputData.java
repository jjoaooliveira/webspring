package com.webapp.usecase.task;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String content,
        OffsetDateTime creationTime,
        OffsetDateTime expirationTime,
        Boolean completed
) {}
