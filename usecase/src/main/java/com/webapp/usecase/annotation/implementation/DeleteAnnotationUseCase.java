package com.webapp.usecase.annotation.implementation;

import com.webapp.usecase.annotation.AnnotationDataGateway;
import com.webapp.usecase.annotation.IDeleteAnnotationUseCase;

import java.util.UUID;

class DeleteAnnotationUseCase implements IDeleteAnnotationUseCase {
    private final AnnotationDataGateway dataGateway;

    public DeleteAnnotationUseCase(AnnotationDataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    @Override
    public void execute(UUID uuid) {
        dataGateway.delete(uuid);
    }
}
