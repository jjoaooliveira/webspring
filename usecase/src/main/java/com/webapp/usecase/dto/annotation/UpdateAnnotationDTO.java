package com.webapp.usecase.dto.annotation;

import java.util.UUID;

public record UpdateAnnotationDTO(
        UUID id,
        String title,
        String content
) {
}
