package com.webapp.repository.mapper;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;
import com.webapp.repository.entity.AnnotationEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntityAnnotationMapper {
    public AnnotationEntity toEntity(Annotation annotation) {
        if(annotation.getId() == null) {
            return new AnnotationEntity(
                    UUID.randomUUID(),
                    annotation.getTitle(),
                    annotation.getContent(),
                    annotation.getCreation()
            );
        }
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
                new TimeMark(entity.getCreation())
        );
    }
}
