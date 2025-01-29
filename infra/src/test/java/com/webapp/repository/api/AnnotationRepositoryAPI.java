package com.webapp.repository.api;

import com.webapp.entity.Annotation;
import com.webapp.repository.AnnotationDataAccessImpl;
import com.webapp.repository.AnnotationRepository;
import com.webapp.repository.mapper.AnnotationMapper;

import java.util.List;
import java.util.UUID;

public class AnnotationRepositoryAPI {
    private AnnotationRepository repository;
    private AnnotationMapper mapper;

    public AnnotationRepositoryAPI(AnnotationRepository repository, AnnotationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Annotation annotationDataAccessSave(Annotation annotation) {
        AnnotationDataAccessImpl annotationDataAccess = new AnnotationDataAccessImpl(repository, mapper);
        return annotationDataAccess.save(annotation);
    }

    public List<Annotation> annotationDataAccessFindAll() {
        AnnotationDataAccessImpl annotationDataAccess = new AnnotationDataAccessImpl(repository, mapper);
        return annotationDataAccess.findAll();
    }

    public void annotationDataAccessDelete(UUID uuid) {
        AnnotationDataAccessImpl annotationDataAccess = new AnnotationDataAccessImpl(repository, mapper);
        annotationDataAccess.delete(uuid);
    }
}
