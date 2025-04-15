package com.webapp.usecase.annotation.implementation;

import com.webapp.usecase.annotation.AnnotationDataGateway;
import com.webapp.usecase.annotation.IDeleteAnnotationUseCase;

import java.util.Objects;
import java.util.UUID;

class DeleteAnnotationUseCase implements IDeleteAnnotationUseCase {
    private final AnnotationDataGateway dataAccess;

    public DeleteAnnotationUseCase(AnnotationDataGateway dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(UUID uuid) {
        Objects.requireNonNull(uuid);
        dataAccess.delete(uuid);
    }
}
