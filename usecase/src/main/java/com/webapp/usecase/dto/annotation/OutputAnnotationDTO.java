package com.webapp.usecase.dto.annotation;

import java.time.OffsetDateTime;

public record OutputAnnotationDTO(
        String id,
        String title,
        String content,
        OffsetDateTime creation) {
}
