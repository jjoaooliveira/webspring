package com.webapp.usecase.task.implementation;

import com.webapp.entity.Content;
import com.webapp.entity.Task;
import com.webapp.entity.Title;
import com.webapp.usecase.task.*;

class SaveTaskUseCase implements ISaveTaskUseCase {

    private final TaskDataGateway taskDataGateway;

    public SaveTaskUseCase(TaskDataGateway taskDataGateway) {
        this.taskDataGateway = taskDataGateway;
    }

    @Override
    public TaskOutputData execute(TaskInputData taskInputData) {
        TaskMapper mapper = new TaskMapper();
        Task newTask = new Task(
                new Title(taskInputData.title()),
                new Content(taskInputData.content()),
                taskInputData.expirationDate(),
                taskInputData.completed()
        );
        Task persistedTask = taskDataGateway.save(newTask);
        return mapper.toOutput(persistedTask);
    }
}
