package com.webapp.usecase.task.implementation;

import com.webapp.entity.*;
import com.webapp.usecase.task.TaskInputData;
import com.webapp.usecase.task.TaskOutputData;

class TaskMapper {
    public TaskOutputData toOutput(Task task) {
        return new TaskOutputData(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getCreation().toString(),
                task.getExpiration().toString(),
                task.isCompleted()
        );
    }

    public Task toEntity(TaskInputData taskInputData) {
        return new Task(
                new Title(taskInputData.title()),
                new Content(taskInputData.content()),
                taskInputData.expirationDate(),
                taskInputData.completed()
        );
    }
}
