package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCase extends UseCase<InputTaskDTO, OutputTaskDTO> {
    private TaskDataAccess dataAccess;
    private TaskMapper mapper;

    @Autowired
    public UpdateTaskUseCase(TaskDataAccess dataAccess, TaskMapper mapper) {
        this.dataAccess = dataAccess;
        this.mapper = mapper;
    }

    @Override
    public OutputTaskDTO execute(InputTaskDTO inputTaskDTO) {
        Task task = dataAccess.findById(inputTaskDTO.id());
        task.setTitle(inputTaskDTO.title());
        task.setContent(inputTaskDTO.content());
        task.setExpiration(inputTaskDTO.expirationDate().toZonedDateTime());

        Task persistedUpdatedTask = dataAccess.save(task);
        return mapper.toOutputDTO(persistedUpdatedTask);
    }
}
