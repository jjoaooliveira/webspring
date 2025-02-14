package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.usecase.ReadByUseCase;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReadTaskByIdUseCase extends ReadByUseCase<UUID, OutputTaskDTO> {
    private TaskDataAccess taskDataAccess;
    private TaskMapper taskMapper;

    @Autowired
    public ReadTaskByIdUseCase(TaskDataAccess taskDataAccess, TaskMapper taskMapper) {
        this.taskDataAccess = taskDataAccess;
        this.taskMapper = taskMapper;
    }

    @Override
    public OutputTaskDTO execute(UUID uuid) {
        Task returnedTask = taskDataAccess.findById(uuid);
        return taskMapper.toOutputDTO(returnedTask);
    }
}
