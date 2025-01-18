package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.data_access.TaskRepository;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveTaskUseCase extends UseCase<InputTaskDTO, OutputTaskDTO> {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public SaveTaskUseCase(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public OutputTaskDTO execute(InputTaskDTO inputTaskDTO) {
        try {
            Task task = taskMapper.toTask(inputTaskDTO);
            Task persistedTask = taskRepository.save(task);

            return taskMapper.toOutputDTO(persistedTask);
        } catch (TextLengthOverLimitException | EmptyTextException e) {
            //TODO implement custom runtime exception
            throw new RuntimeException(e);
        }
    }
}
