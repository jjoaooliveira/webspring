package com.webapp.usecase.task;

import com.webapp.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

class UpdateTaskUseCase implements IUpdateTaskUseCase {
    private final TaskDataAccess dataAccess;
    private final TaskMapper mapper;

    @Autowired
    public UpdateTaskUseCase(TaskDataAccess dataAccess) {
        this.dataAccess = dataAccess;
        this.mapper = new TaskMapper();
    }

    @Override
    public OutputTaskDTO execute(InputTaskDTO request) {
        Task task = dataAccess.findById(request.id());
        task.setTitle(request.title());
        task.setContent(request.content());
        task.setExpiration(request.expiration().toZonedDateTime());

        Task persistedUpdatedTask = dataAccess.save(task);
        return mapper.toOutputDTO(persistedUpdatedTask);
    }
}
