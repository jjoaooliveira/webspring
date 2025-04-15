package com.webapp.repository.api;

import com.webapp.entity.Annotation;
import com.webapp.repository.AnnotationDataAccessImpl;
import com.webapp.repository.AnnotationRepository;
import com.webapp.repository.mapper.EntityAnnotationMapper;

import java.util.List;
import java.util.UUID;

public class AnnotationRepositoryAPI {
    private AnnotationDataAccessImpl annotationDataAccess;

    public AnnotationRepositoryAPI(AnnotationRepository repository, EntityAnnotationMapper mapper) {
        annotationDataAccess = new AnnotationDataAccessImpl(repository, mapper);
    }

    public Annotation annotationDataAccessSave(Annotation annotation) {
        return annotationDataAccess.save(annotation);
    }

    public List<Annotation> annotationDataAccessFindAll() {
        return annotationDataAccess.findAll();
    }

    public List<Annotation> annotationDataAccessFindByTitle(String title) {
        return annotationDataAccess.findByTitle(title);
    }

    public Annotation annotationDataAccessFindById(UUID uuid) {
        return annotationDataAccess.findById(uuid);
    }

    public void annotationDataAccessDelete(UUID uuid) {
        annotationDataAccess.delete(uuid);
    }
}
