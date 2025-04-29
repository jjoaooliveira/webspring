package com.webapp.usecase.task.implementation;

import com.webapp.entity.Task;
import com.webapp.usecase.task.*;

class SaveTask implements ISaveTaskUseCase {

    private final TaskDataGateway taskDataGateway;

    public SaveTask(TaskDataGateway taskDataGateway) {
        this.taskDataGateway = taskDataGateway;
    }

    @Override
    public TaskOutputData execute(TaskInputData taskInputData) {
        TaskMapper mapper = new TaskMapper();
        Task newTask = mapper.toEntity(taskInputData);
        Task persistedTask = taskDataGateway.save(newTask);
        return mapper.toOutput(persistedTask);
    }
}
