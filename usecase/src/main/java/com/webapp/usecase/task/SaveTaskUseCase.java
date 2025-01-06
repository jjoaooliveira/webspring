package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.dataaccess.TaskRepository;
import com.webapp.usecase.dto.task.DatabaseTaskDTO;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.DatabaseMapper;
import com.webapp.usecase.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveTaskUseCase extends UseCase<InputTaskDTO, OutputTaskDTO> {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final DatabaseMapper databaseMapper;

    @Autowired
    public SaveTaskUseCase(TaskRepository taskRepository, TaskMapper taskMapper, DatabaseMapper databaseMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.databaseMapper = databaseMapper;
    }

    @Override
    public OutputTaskDTO execute(InputTaskDTO inputTaskDTO) {
        try {
            Task task = taskMapper.createEntity(inputTaskDTO);
            DatabaseTaskDTO databaseTaskDTO = databaseMapper.createDTO(task);
            DatabaseTaskDTO returnedDatabaseTask = taskRepository.save(databaseTaskDTO);
            Task returnedDataBaseTask = databaseMapper.createEntity(returnedDatabaseTask);
            return taskMapper.createDTO(returnedDataBaseTask);
        } catch (TextLengthOverLimitException | EmptyTextException e) {
            throw new RuntimeException(e);
        }
    }
}
