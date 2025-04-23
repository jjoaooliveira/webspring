package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;

import java.util.List;
import java.util.UUID;

public interface AnnotationDataGateway {
    List<Annotation> findByTitle(String title);
    Annotation findById(UUID uuid);
    List<Annotation> findAll();
    Annotation save(Annotation annotation);
    void delete(UUID id);
}
