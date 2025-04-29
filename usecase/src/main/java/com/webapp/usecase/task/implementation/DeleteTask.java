package com.webapp.usecase.task.implementation;

import com.webapp.usecase.task.IDeleteTaskUseCase;
import com.webapp.usecase.task.TaskDataGateway;

import java.util.UUID;

class DeleteTask implements IDeleteTaskUseCase {
    private final TaskDataGateway dataGateway;

    public DeleteTask(TaskDataGateway dataGateway) {
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
