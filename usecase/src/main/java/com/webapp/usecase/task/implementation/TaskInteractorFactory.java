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
        return new SaveTask(dataGateway);
    }

    @Override
    public IUpdateTaskUseCase makeUpdateTaskUseCase() {
        return new UpdateTask(dataGateway);
    }

    @Override
    public IDeleteTaskUseCase makeDeleteTaskUseCase() {
        return new DeleteTask(dataGateway);
    }

    @Override
    public IReadAllTaskUseCase makeReadAllTaskUseCase() {
        return new ReadAllTask(dataGateway);
    }

    @Override
    public IReadTaskByTitleUseCase makeReadTaskByTitleUseCase() {
        return new ReadTaskByTitle(dataGateway);
    }

    @Override
    public IReadTaskByIdUseCase makeReadTaskByIdUseCase() {
        return new ReadTaskById(dataGateway);
    }
}
