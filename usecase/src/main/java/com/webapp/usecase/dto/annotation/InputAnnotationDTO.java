package com.webapp.usecase.dto.annotation;

import java.time.OffsetDateTime;
import java.util.UUID;

public record InputAnnotationDTO(
        UUID id,
        String title,
        String content,
        OffsetDateTime creation) {
}
