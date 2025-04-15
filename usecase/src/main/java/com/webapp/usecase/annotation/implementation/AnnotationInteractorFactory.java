package com.webapp.usecase.annotation.implementation;

import com.webapp.usecase.annotation.*;

class AnnotationInteractorFactory extends AbstractAnnotationInteractorFactory {
    private final AnnotationDataGateway dataGateway;

    public AnnotationInteractorFactory(AnnotationDataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    @Override
    public ISaveAnnotationUseCase makeSaveInteractor() {
        return new SaveAnnotationUseCase(dataGateway);
    }

    @Override
    public IUpdateAnnotationUseCase makeUpdateInteractor() {
        return new UpdateAnnotationUseCase(dataGateway);
    }

    @Override
    public IDeleteAnnotationUseCase makeDeleteInteractor() {
        return new DeleteAnnotationUseCase(dataGateway);
    }

    @Override
    public IReadAllAnnotationUseCase makeReadAllInteractor() {
        return new ReadAllAnnotationUseCase(dataGateway);
    }

    @Override
    public IReadAnnotationByIdUseCase makeReadByIdInteractor() {
        return new ReadAnnotationByIdUseCase(dataGateway);
    }

    @Override
    public IReadAnnotationByTitleUseCase makeReadByTitleInteractor() {
        return new ReadAnnotationByTitleUseCase(dataGateway);
    }
}
