package com.webapp.usecase.task.implementation;

import com.webapp.usecase.task.IDeleteTaskUseCase;
import com.webapp.usecase.task.TaskDataGateway;

import java.util.UUID;

class DeleteTaskUseCase implements IDeleteTaskUseCase {
    private final TaskDataGateway dataGateway;

    public DeleteTaskUseCase(TaskDataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    @Override
    public void execute(UUID uuid) {
        if (dataGateway.findById(uuid) == null) {
            throw new IllegalArgumentException("Task not found");
        }
        dataGateway.delete(uuid);
    }
}
