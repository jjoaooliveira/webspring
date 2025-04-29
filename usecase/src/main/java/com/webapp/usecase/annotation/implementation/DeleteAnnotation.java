package com.webapp.usecase.annotation.implementation;

import com.webapp.usecase.annotation.AnnotationDataGateway;
import com.webapp.usecase.annotation.IDeleteAnnotationUseCase;

import java.util.UUID;

class DeleteAnnotation implements IDeleteAnnotationUseCase {
    private final AnnotationDataGateway dataGateway;

    public DeleteAnnotation(AnnotationDataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    @Override
    public void execute(UUID uuid) {
        dataGateway.delete(uuid);
    }
}
