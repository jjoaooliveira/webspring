package com.webapp.usecase.task;

import java.time.Instant;
import java.util.UUID;

public record TaskInputData(
        UUID id,
        String title,
        String content,
        Instant expirationDate,
        Boolean completed
) {}
