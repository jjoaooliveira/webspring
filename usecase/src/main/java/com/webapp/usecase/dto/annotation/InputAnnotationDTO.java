package com.webapp.usecase.dto.annotation;

import java.util.Optional;

public record InputAnnotationDTO(
        Optional<String> id,
        String title,
        String content,
        String zone) {
}
