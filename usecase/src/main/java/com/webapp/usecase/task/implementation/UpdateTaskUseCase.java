package com.webapp.usecase.task.implementation;

import com.webapp.entity.Task;
import com.webapp.usecase.task.IUpdateTaskUseCase;
import com.webapp.usecase.task.TaskDataGateway;
import com.webapp.usecase.task.TaskInputData;
import com.webapp.usecase.task.TaskOutputData;

class UpdateTaskUseCase implements IUpdateTaskUseCase {
    private final TaskDataGateway dataGateway;

    public UpdateTaskUseCase(TaskDataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    @Override
    public TaskOutputData execute(TaskInputData taskInputData) {
        TaskMapper mapper = new TaskMapper();
        Task requestTask = dataGateway.findById(taskInputData.id());
        requestTask.setTitle(taskInputData.title());
        requestTask.setContent(taskInputData.content());
        requestTask.setExpiration(taskInputData.expirationDate());
        requestTask.setCompleted(taskInputData.completed());
        Task updatedTask = dataGateway.save(requestTask);
        return mapper.toOutput(updatedTask);
    }
}
