package com.webapp.usecase.task;

import java.time.OffsetDateTime;
import java.util.UUID;

public record Response(
        UUID id,
        String title,
        String content,
        OffsetDateTime creation,
        OffsetDateTime expiration,
        boolean completed
) {}
