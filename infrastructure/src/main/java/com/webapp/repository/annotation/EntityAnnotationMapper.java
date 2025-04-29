package com.webapp.repository.annotation;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.Title;
import org.springframework.stereotype.Component;

@Component
class EntityAnnotationMapper {
    public AnnotationEntity toEntity(Annotation annotation) {
        return new AnnotationEntity(
                annotation.getId(),
                annotation.getTitle(),
                annotation.getContent(),
                annotation.getCreation()
        );
    }

    public Annotation toAnnotation(AnnotationEntity entity) {
        return new Annotation(
                entity.getUUID(),
                new Title(entity.getTitle()),
                new Content(entity.getContent()),
                entity.getCreation()
        );
    }
}
