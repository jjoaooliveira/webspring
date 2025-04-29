package com.webapp.usecase.annotation.implementation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.annotation.*;

import java.util.List;

class ReadAnnotationByTitle implements IReadAnnotationByTitleUseCase {
    private final AnnotationDataGateway annotationDataGateway;

    public ReadAnnotationByTitle(AnnotationDataGateway annotationDataGateway) {
        this.annotationDataGateway = annotationDataGateway;
    }

    @Override
    public List<AnnotationOutputData> execute(String title) {
        AnnotationMapper mapper = new AnnotationMapper();
        List<Annotation> annotationList = annotationDataGateway.findByTitle(title);
        return annotationList
                .stream()
                .map(mapper::toOutput)
                .toList();
    }
}
