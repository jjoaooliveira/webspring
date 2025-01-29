package com.webapp.repository;

import com.webapp.entity.Annotation;
import com.webapp.repository.entity.AnnotationEntity;
import com.webapp.repository.mapper.AnnotationMapper;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AnnotationDataAccessImpl implements AnnotationDataAccess {
    private AnnotationRepository annotationRepository;
    private AnnotationMapper annotationMapper;

    public AnnotationDataAccessImpl(AnnotationRepository annotationRepository, AnnotationMapper annotationMapper) {
        this.annotationRepository = annotationRepository;
        this.annotationMapper = annotationMapper;
    }

    @Override
    public List<Annotation> findAll() {
        List<AnnotationEntity> annotationEntityList = annotationRepository.findAll();
        return annotationEntityList.stream()
                .map(annotationMapper::toAnnotation)
                .toList();
    }

    @Override
    public Annotation save(Annotation annotation) {
        AnnotationEntity annotationEntity = annotationMapper.toEntity(annotation);
        AnnotationEntity persistedAnnotationEntity = annotationRepository.save(annotationEntity);
        return annotationMapper.toAnnotation(persistedAnnotationEntity);
    }

    @Override
    public void delete(UUID id) {
        annotationRepository.deleteById(id);
    }
}
