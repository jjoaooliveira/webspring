package com.webapp.usecase.data_access;

import com.webapp.entity.Annotation;

import java.util.List;

public interface AnnotationDataAccess {
    List<Annotation> findAll();
    Annotation save(Annotation annotation);
    void delete(String id);
}
