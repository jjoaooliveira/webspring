package com.webapp.usecase.task;

public abstract class AbstractTaskInteractorFactory {
    public abstract ISaveTaskUseCase makeSaveTaskUseCase();
    public abstract IUpdateTaskUseCase makeUpdateTaskUseCase();
    public abstract IDeleteTaskUseCase makeDeleteTaskUseCase();
    public abstract IReadAllTaskUseCase makeReadAllTaskUseCase();
    public abstract IReadTaskByTitleUseCase makeReadTaskByTitleUseCase();
    public abstract IReadTaskByIdUseCase makeReadTaskByIdUseCase();
}
