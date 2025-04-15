package com.webapp.usecase.annotation.implementation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.annotation.*;

class UpdateAnnotationUseCase implements IUpdateAnnotationUseCase {
    private final AnnotationDataGateway dataAccess;

    public UpdateAnnotationUseCase(AnnotationDataGateway dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public AnnotationOutpuData execute(AnnotationInputData annotationInputData) {
        AnnotationMapper mapper = new AnnotationMapper();
        Annotation requestedAnnotation = dataAccess.findById(annotationInputData.id());
        requestedAnnotation.setTitle(annotationInputData.title());
        requestedAnnotation.setContent(annotationInputData.content());
        Annotation persistedUpdatedAnnotation = dataAccess.save(requestedAnnotation);
        return mapper.toOutput(persistedUpdatedAnnotation);
    }
}
