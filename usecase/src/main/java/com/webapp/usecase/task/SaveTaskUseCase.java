package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.usecase.SaveUseCase;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveTaskUseCase extends SaveUseCase<InputTaskDTO, OutputTaskDTO> {

    private final TaskDataAccess taskDataAccess;
    private final TaskMapper taskMapper;

    @Autowired
    public SaveTaskUseCase(TaskDataAccess taskDataAccess, TaskMapper taskMapper) {
        this.taskDataAccess = taskDataAccess;
        this.taskMapper = taskMapper;
    }

    @Override
    public OutputTaskDTO execute(InputTaskDTO inputTaskDTO) {
        Task task = taskMapper.toTask(inputTaskDTO);
        Task persistedTask = taskDataAccess.save(task);

        return taskMapper.toOutputDTO(persistedTask);
    }
}
