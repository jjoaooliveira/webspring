package com.webapp.usecase.task.implementation;

import com.webapp.entity.Task;
import com.webapp.usecase.task.IReadAllTaskUseCase;
import com.webapp.usecase.task.TaskDataGateway;
import com.webapp.usecase.task.TaskOutputData;

import java.util.List;

class ReadAllTask implements IReadAllTaskUseCase {

    private final TaskDataGateway taskDataGateway;

    public ReadAllTask(TaskDataGateway taskDataGateway) {
        this.taskDataGateway = taskDataGateway;
    }

    @Override
    public List<TaskOutputData> execute() {
        TaskMapper taskMapper = new TaskMapper();
        List<Task> persistedTasks = taskDataGateway.findAll();
        return persistedTasks.stream()
            .map(taskMapper::toOutput).toList();
    }
}

