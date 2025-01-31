package com.webapp.usecase.dto.task;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public record InputTaskDTO(
        Optional<UUID> id,
        String title,
        String content,
        OffsetDateTime expirationDate,
        Boolean completed
) {}
