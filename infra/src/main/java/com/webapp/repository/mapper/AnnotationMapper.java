package com.webapp.repository.mapper;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;
import com.webapp.repository.entity.AnnotationEntity;

public class AnnotationMapper {
    public AnnotationEntity toEntity(Annotation annotation) {
        AnnotationEntity entity = new AnnotationEntity();
        if(annotation.getId() != null) entity.setUUID(annotation.getId());
        entity.setTitle(annotation.getTitle());
        entity.setContent(annotation.getContent());
        entity.setCreation(annotation.getCreation());

        return entity;
    }

    public Annotation toAnnotation(AnnotationEntity entity) {
        return new Annotation(
                entity.getUUID(),
                new Title(entity.getTitle()),
                new Content(entity.getContent()),
                new TimeMark(entity.getCreation())
        );
    }
}
