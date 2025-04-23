package com.webapp.usecase.annotation;

import java.util.UUID;

public record AnnotationInputData(
    UUID id,
    String title,
    String content) {
}
