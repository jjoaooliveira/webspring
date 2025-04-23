package com.webapp.usecase.annotation.implementation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.annotation.*;

class UpdateAnnotationUseCase implements IUpdateAnnotationUseCase {
    private final AnnotationDataGateway dataGateway;

    public UpdateAnnotationUseCase(AnnotationDataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    @Override
    public AnnotationOutputData execute(AnnotationInputData updateAnnotationInputData) {
        AnnotationMapper mapper = new AnnotationMapper();
        Annotation requestAnnotation = dataGateway.findById(updateAnnotationInputData.id());
        requestAnnotation.setContent(updateAnnotationInputData.content());
        requestAnnotation.setTitle(updateAnnotationInputData.title());
        Annotation updatedAnnotation = dataGateway.save(requestAnnotation);
        return mapper.toOutput(updatedAnnotation);
    }
}
