package com.webapp.usecase.dto.annotation;

public record OutputAnnotationDTO(
        String id,
        String title,
        String content,
        String creationDate,
        String creationTime) {
}
