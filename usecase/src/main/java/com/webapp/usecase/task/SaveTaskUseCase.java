package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.dataaccess.TaskRepository;
import com.webapp.usecase.dto.task.NewTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.dto.task.TaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveTaskUseCase extends UseCase<NewTaskDTO, OutputTaskDTO> {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public SaveTaskUseCase(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public OutputTaskDTO execute(NewTaskDTO newTaskDTO) {
        try {
            Task task = taskMapper.toNewTask(newTaskDTO);
            TaskDTO taskDTO = taskMapper.toTaskDTO(task);
            TaskDTO returnedDatabaseTask = taskRepository.save(taskDTO);
            Task returnedPersistedTask = taskMapper.toTask(returnedDatabaseTask);
            return taskMapper.toOutputDTO(returnedPersistedTask);
        } catch (TextLengthOverLimitException | EmptyTextException e) { //TODO implement custom exception
            throw new RuntimeException(e);
        }
    }
}
