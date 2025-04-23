package com.webapp.usecase.task.implementation;

import com.webapp.usecase.task.AbstractTaskInteractorFactory;
import com.webapp.usecase.task.ISaveTaskUseCase;
import com.webapp.usecase.task.IUpdateTaskUseCase;
import com.webapp.usecase.task.TaskDataGateway;
import com.webapp.usecase.task.IDeleteTaskUseCase;
import com.webapp.usecase.task.IReadAllTaskUseCase;
import com.webapp.usecase.task.IReadTaskByIdUseCase;
import com.webapp.usecase.task.IReadTaskByTitleUseCase;

public class TaskInteractorFactory extends AbstractTaskInteractorFactory {
    private final TaskDataGateway dataGateway;

    public TaskInteractorFactory(TaskDataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    @Override
    public ISaveTaskUseCase makeSaveTaskUseCase() {
        return new SaveTaskUseCase(dataGateway);
    }

    @Override
    public IUpdateTaskUseCase makeUpdateTaskUseCase() {
        return new UpdateTaskUseCase(dataGateway);
    }

    @Override
    public IDeleteTaskUseCase makeDeleteTaskUseCase() {
        return new DeleteTaskUseCase(dataGateway);
    }

    @Override
    public IReadAllTaskUseCase makeReadAllTaskUseCase() {
        return new ReadAllTaskUseCase(dataGateway);
    }

    @Override
    public IReadTaskByTitleUseCase makeReadTaskByTitleUseCase() {
        return new ReadTaskByTitleUseCase(dataGateway);
    }

    @Override
    public IReadTaskByIdUseCase makeReadTaskByIdUseCase() {
        return new ReadTaskByIdUseCase(dataGateway);
    }
}
