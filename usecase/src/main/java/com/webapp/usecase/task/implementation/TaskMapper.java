package com.webapp.usecase.task.implementation;

import com.webapp.entity.*;
import com.webapp.usecase.task.TaskOutputData;

class TaskMapper {
    public TaskOutputData toOutput(Task task) {
        return new TaskOutputData(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getCreation(),
                task.getExpiration(),
                task.isCompleted()
        );
    }
}
