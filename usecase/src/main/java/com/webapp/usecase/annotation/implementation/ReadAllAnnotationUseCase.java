package com.webapp.usecase.annotation.implementation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.annotation.AnnotationDataGateway;
import com.webapp.usecase.annotation.AnnotationOutpuData;
import com.webapp.usecase.annotation.IReadAllAnnotationUseCase;

import java.util.List;

class ReadAllAnnotationUseCase implements IReadAllAnnotationUseCase {
    private final AnnotationDataGateway annotationDataGateway;

    public ReadAllAnnotationUseCase(AnnotationDataGateway annotationDataGateway) {
        this.annotationDataGateway = annotationDataGateway;
    }

    @Override
    public List<AnnotationOutpuData> execute() {
        AnnotationMapper mapper = new AnnotationMapper();
        List<Annotation> annotations = annotationDataGateway.findAll();
        return annotations.stream()
            .map(mapper::toOutput)
            .toList();
    }
}
