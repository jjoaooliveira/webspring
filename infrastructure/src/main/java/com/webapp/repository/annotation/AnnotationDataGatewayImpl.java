package com.webapp.repository.annotation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.annotation.AnnotationDataGateway;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
class AnnotationDataGatewayImpl implements AnnotationDataGateway {
    private final AnnotationRepository repository;
    private final EntityAnnotationMapper mapper;

    @Autowired
    public AnnotationDataGatewayImpl(AnnotationRepository repository, EntityAnnotationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
