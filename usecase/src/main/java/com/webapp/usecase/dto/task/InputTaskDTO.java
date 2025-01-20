package com.webapp.usecase.dto.task;

import java.time.OffsetDateTime;

public record InputTaskDTO(
        String title,
        String content,
        OffsetDateTime expirationDate
) {}
