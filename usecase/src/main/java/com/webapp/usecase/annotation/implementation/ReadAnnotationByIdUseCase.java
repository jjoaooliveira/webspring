package com.webapp.usecase.annotation.implementation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.annotation.*;

import java.util.UUID;

class ReadAnnotationByIdUseCase implements IReadAnnotationByIdUseCase {
    private final AnnotationDataGateway annotationDataGateway;

    public ReadAnnotationByIdUseCase(AnnotationDataGateway annotationDataGateway) {
        this.annotationDataGateway = annotationDataGateway;
    }

    @Override
    public AnnotationOutpuData execute(UUID uuid) {
        AnnotationMapper mapper = new AnnotationMapper();
        Annotation returnedAnnotation = annotationDataGateway.findById(uuid);
        return mapper.toOutput(returnedAnnotation);
    }
}
