package com.webapp.usecase.annotation.implementation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.annotation.*;

class SaveAnnotationUseCase implements ISaveAnnotationUseCase {
    private final AnnotationDataGateway dataAccess;

    public SaveAnnotationUseCase(AnnotationDataGateway dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public AnnotationOutpuData execute(AnnotationInputData annotationInputData) {
        AnnotationMapper mapper = new AnnotationMapper();
        Annotation requestAnnotation = mapper.toAnnotation(annotationInputData);
        Annotation savedAnnotation = dataAccess.save(requestAnnotation);
        return mapper.toOutput(savedAnnotation);
    }
}
