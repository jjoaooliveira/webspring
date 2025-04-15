package com.webapp.usecase.annotation.implementation;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.Title;
import com.webapp.usecase.annotation.AnnotationInputData;
import com.webapp.usecase.annotation.AnnotationOutpuData;

import java.time.Instant;

class AnnotationMapper {
    public AnnotationOutpuData toOutput(Annotation annotation) {
        return new AnnotationOutpuData(
                annotation.getId(),
                annotation.getTitle(),
                annotation.getContent(),
                annotation.getCreation()
        );
    }

    public Annotation toAnnotation(AnnotationInputData annotationInputData) {
        return new Annotation(
                annotationInputData.id(),
                new Title(annotationInputData.title()),
                new Content(annotationInputData.content()),
                Instant.now()
            );
    }
}
