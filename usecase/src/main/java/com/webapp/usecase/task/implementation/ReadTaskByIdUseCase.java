package com.webapp.usecase.task;

import com.webapp.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class ReadTaskByIdUseCase implements IReadTaskByIdUseCase {
    private final TaskDataAccess taskDataAccess;
    private final TaskMapper taskMapper;

    @Autowired
    public ReadTaskByIdUseCase(TaskDataAccess taskDataAccess) {
        this.taskDataAccess = taskDataAccess;
        this.taskMapper = new TaskMapper();
    }

    @Override
    public OutputTaskDTO execute(InputTaskDTO request) {
        Task returnedTask = taskDataAccess.findById(request.id());
        return taskMapper.toOutputDTO(returnedTask);
    }
}
