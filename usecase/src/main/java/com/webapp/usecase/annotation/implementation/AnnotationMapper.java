package com.webapp.usecase.annotation.implementation;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.Title;
import com.webapp.usecase.annotation.AnnotationInputData;
import com.webapp.usecase.annotation.AnnotationOutputData;

class AnnotationMapper {
    public AnnotationOutputData toOutput(Annotation annotation) {
        return new AnnotationOutputData(
                annotation.getId(),
                annotation.getTitle(),
                annotation.getContent(),
                annotation.getCreation().toString()
        );
    }

    public Annotation toEntity(AnnotationInputData annotationInputData) {
        return new Annotation(
            new Title(annotationInputData.title()),
            new Content(annotationInputData.content())
        );
    }
}
