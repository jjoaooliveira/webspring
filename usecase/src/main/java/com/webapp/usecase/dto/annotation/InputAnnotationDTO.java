package com.webapp.usecase.dto.annotation;

import java.util.Optional;
import java.util.UUID;

public record InputAnnotationDTO(
        Optional<UUID> id,
        String title,
        String content) {
}
