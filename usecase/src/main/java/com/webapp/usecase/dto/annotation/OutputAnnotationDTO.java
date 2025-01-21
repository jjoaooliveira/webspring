package com.webapp.usecase.dto.annotation;

import java.time.OffsetDateTime;
import java.util.UUID;

public record OutputAnnotationDTO(
        UUID id,
        String title,
        String content,
        OffsetDateTime creation) {
}
