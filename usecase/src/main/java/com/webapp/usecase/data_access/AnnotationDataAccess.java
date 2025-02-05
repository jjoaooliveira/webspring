package com.webapp.usecase.data_access;

import com.webapp.entity.Annotation;

import java.util.List;
import java.util.UUID;

public interface AnnotationDataAccess {
    List<Annotation> findByTitle(String title);
    Annotation findById(UUID uuid);
    List<Annotation> findAll();
    Annotation save(Annotation annotation);
    void delete(UUID id);
}
