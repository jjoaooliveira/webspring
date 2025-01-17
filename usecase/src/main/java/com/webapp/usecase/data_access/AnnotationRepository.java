package com.webapp.usecase.data_access;

import com.webapp.entity.Annotation;

import java.util.List;

public interface AnnotationRepository {
    List<Annotation> findAll();
    Annotation save(Annotation annotation);
    void delete(String id);
}
