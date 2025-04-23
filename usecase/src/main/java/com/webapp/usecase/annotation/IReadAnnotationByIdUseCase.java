package com.webapp.usecase.annotation;

import java.util.UUID;

public interface IReadAnnotationByIdUseCase {
    AnnotationOutputData execute(UUID uuid);
}
