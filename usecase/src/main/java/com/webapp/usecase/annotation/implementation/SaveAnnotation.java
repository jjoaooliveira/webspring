package com.webapp.usecase.annotation.implementation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.annotation.*;

class SaveAnnotation implements ISaveAnnotationUseCase {
    private final AnnotationDataGateway dataAccess;

    public SaveAnnotation(AnnotationDataGateway dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public AnnotationOutputData execute(AnnotationInputData saveAnnotationInputData) {
        AnnotationMapper mapper = new AnnotationMapper();
        Annotation newAnnotation = mapper.toEntity(saveAnnotationInputData);
        Annotation savedAnnotation = dataAccess.save(newAnnotation);
        return mapper.toOutput(savedAnnotation);
    }
}
