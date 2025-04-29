package com.webapp.usecase.annotation.implementation;

import com.webapp.usecase.annotation.AbstractAnnotationInteractorFactory;
import com.webapp.usecase.annotation.AnnotationDataGateway;
import com.webapp.usecase.annotation.ISaveAnnotationUseCase;
import com.webapp.usecase.annotation.IUpdateAnnotationUseCase;
import com.webapp.usecase.annotation.IReadAllAnnotationUseCase;
import com.webapp.usecase.annotation.IDeleteAnnotationUseCase;
import com.webapp.usecase.annotation.IReadAnnotationByIdUseCase;
import com.webapp.usecase.annotation.IReadAnnotationByTitleUseCase;

public class AnnotationInteractorFactory extends AbstractAnnotationInteractorFactory {
    private final AnnotationDataGateway dataGateway;

    public AnnotationInteractorFactory(AnnotationDataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    @Override
    public ISaveAnnotationUseCase makeSaveInteractor() {
        return new SaveAnnotation(dataGateway);
    }

    @Override
    public IUpdateAnnotationUseCase makeUpdateInteractor() {
        return new UpdateAnnotation(dataGateway);
    }

    @Override
    public IDeleteAnnotationUseCase makeDeleteInteractor() {
        return new DeleteAnnotation(dataGateway);
    }

    @Override
    public IReadAllAnnotationUseCase makeReadAllInteractor() {
        return new ReadAllAnnotation(dataGateway);
    }

    @Override
    public IReadAnnotationByIdUseCase makeReadByIdInteractor() {
        return new ReadAnnotationById(dataGateway);
    }

    @Override
    public IReadAnnotationByTitleUseCase makeReadByTitleInteractor() {
        return new ReadAnnotationByTitle(dataGateway);
    }
}
