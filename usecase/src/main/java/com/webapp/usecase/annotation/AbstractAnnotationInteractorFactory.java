package com.webapp.usecase.annotation;

public abstract class AbstractAnnotationInteractorFactory {
    public abstract ISaveAnnotationUseCase makeSaveInteractor();
    public abstract IUpdateAnnotationUseCase makeUpdateInteractor();
    public abstract IDeleteAnnotationUseCase makeDeleteInteractor();
    public abstract IReadAllAnnotationUseCase makeReadAllInteractor();
    public abstract IReadAnnotationByIdUseCase makeReadByIdInteractor();
    public abstract IReadAnnotationByTitleUseCase makeReadByTitleInteractor();
}
