package com.webapp.usecase.dto.annotation;

public record UpdateAnnotationDTO(
        String id,
        String title,
        String content
) {
}
