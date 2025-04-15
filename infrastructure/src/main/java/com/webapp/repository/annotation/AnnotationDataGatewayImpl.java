package com.webapp.repository.annotation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.annotation.AnnotationDataAccess;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class AnnotationDataAccessImpl implements AnnotationDataAccess {
    private AnnotationRepository repository;
    private final EntityAnnotationMapper mapper;

    public AnnotationDataAccessImpl() {
        this.mapper = new EntityAnnotationMapper();
    }

    @Override
    public List<Annotation> findByTitle(String title) {
        List<AnnotationEntity> annotationEntityList = repository.findByTitle(title);
        return annotationEntityList.stream()
                .map(mapper::toAnnotation)
                .toList();
    }

    @Override
    public Annotation findById(UUID uuid) {
        Optional<AnnotationEntity> annotationEntity = repository.findById(uuid);
        return mapper.toAnnotation(annotationEntity
                .orElseThrow(() -> new EntityNotFoundException("Annotation does not exist with given id"))
        );
    }

    @Override
    public List<Annotation> findAll() {
        List<AnnotationEntity> annotationEntityList = repository.findAll();
        return annotationEntityList.stream()
                .map(mapper::toAnnotation)
                .toList();
    }

    @Override
    public Annotation save(Annotation annotation) {
        AnnotationEntity annotationEntity = mapper.toEntity(annotation);
        AnnotationEntity persistedAnnotationEntity = repository.save(annotationEntity);
        return mapper.toAnnotation(persistedAnnotationEntity);
    }

    @Override
    public Annotation delete(UUID id) {
        repository.deleteById(id);
    }

    public void setRepository(AnnotationRepository repository) {
        this.repository = repository;
    }
}
