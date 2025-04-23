package com.webapp.usecase.task.implementation;

import com.webapp.entity.Task;

import com.webapp.usecase.task.IReadTaskByTitleUseCase;
import com.webapp.usecase.task.TaskDataGateway;
import com.webapp.usecase.task.TaskOutputData;

import java.util.List;

class ReadTaskByTitleUseCase implements IReadTaskByTitleUseCase {
    private final TaskDataGateway taskDataGateway;

    public ReadTaskByTitleUseCase(TaskDataGateway taskDataGateway) {
        this.taskDataGateway = taskDataGateway;
    }

    @Override
    public List<TaskOutputData> execute(String title) {
        TaskMapper taskMapper = new TaskMapper();
        List<Task> taskList = taskDataGateway.findByTitle(title);
        return taskList.stream()
                .map(taskMapper::toOutput)
                .toList();
    }
}
