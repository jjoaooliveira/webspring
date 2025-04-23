package com.webapp.usecase.task;

import java.time.Instant;
import java.util.UUID;

public record TaskOutputData(
        UUID uuid,
        String title,
        String content,
        Instant creation,
        Instant expiration,
        boolean completed
) {}
