package com.webapp.usecase.task;

import com.webapp.entity.Task;

import java.util.List;

class ReadAllTaskUseCase implements IReadAllTaskUseCase {

    private final TaskDataAccess taskDataAccess;
    private final TaskMapper taskMapper;

    public ReadAllTaskUseCase(TaskDataAccess taskDataAccess) {
        this.taskDataAccess = taskDataAccess;
        this.taskMapper = new TaskMapper();
    }

    @Override
    public List<OutputTaskDTO> execute() {
        List<Task> persistedTasks = taskDataAccess.findAll();

        return persistedTasks.stream()
            .map(taskMapper::toOutputDTO).toList();
    }
}

