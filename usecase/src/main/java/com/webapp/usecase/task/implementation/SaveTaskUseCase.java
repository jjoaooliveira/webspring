package com.webapp.usecase.task;

import com.webapp.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

class SaveTaskUseCase implements ISaveTaskUseCase {

    private final TaskDataAccess taskDataAccess;
    private final TaskMapper taskMapper;

    public SaveTaskUseCase(TaskDataAccess taskDataAccess) {
        this.taskDataAccess = taskDataAccess;
        this.taskMapper = new TaskMapper();
    }

    @Override
    public OutputTaskDTO execute(InputTaskDTO inputTaskDTO) {
        Task task = taskMapper.toTask(inputTaskDTO);
        Task persistedTask = taskDataAccess.save(task);

        return taskMapper.toOutputDTO(persistedTask);
    }
}
