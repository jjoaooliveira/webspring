package com.webapp.usecase.task;

import com.webapp.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class ReadTaskByTitleUseCase implements IReadTaskByTitleUseCase {
    private final TaskDataAccess taskDataAccess;
    private final TaskMapper taskMapper;

    @Autowired
    public ReadTaskByTitleUseCase(TaskDataAccess taskDataAccess) {
        this.taskDataAccess = taskDataAccess;
        this.taskMapper = new TaskMapper();
    }

    @Override
    public List<OutputTaskDTO> execute(InputTaskDTO request) {
        List<Task> taskList = taskDataAccess.findByTitle(request.title());
        return taskList.stream()
                .map(taskMapper::toOutputDTO)
                .toList();
    }
}
