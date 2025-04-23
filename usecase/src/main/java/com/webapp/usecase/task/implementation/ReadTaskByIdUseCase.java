package com.webapp.usecase.task.implementation;

import com.webapp.entity.Task;
import com.webapp.usecase.task.IReadTaskByIdUseCase;
import com.webapp.usecase.task.TaskDataGateway;
import com.webapp.usecase.task.TaskOutputData;

import java.util.UUID;

class ReadTaskByIdUseCase implements IReadTaskByIdUseCase {
    private final TaskDataGateway taskDataGateway;

    public ReadTaskByIdUseCase(TaskDataGateway taskDataGateway) {
        this.taskDataGateway = taskDataGateway;
    }

    @Override
    public TaskOutputData execute(UUID uuid) {
        TaskMapper taskMapper = new TaskMapper();
        Task returnedTask = taskDataGateway.findById(uuid);
        return taskMapper.toOutput(returnedTask);
    }
}
