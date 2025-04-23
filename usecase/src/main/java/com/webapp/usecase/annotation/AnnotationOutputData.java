package com.webapp.usecase.annotation;

import java.time.Instant;
import java.util.UUID;

public record AnnotationOutputData(
        UUID id,
        String title,
        String content,
        Instant creationDate
) {
}
