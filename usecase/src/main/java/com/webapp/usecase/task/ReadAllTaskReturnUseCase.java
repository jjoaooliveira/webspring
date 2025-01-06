package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.SimpleReturnUseCase;
import com.webapp.usecase.dataaccess.TaskRepository;
import com.webapp.usecase.dto.task.DatabaseTaskDTO;

import java.util.List;

import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.DatabaseMapper;
import com.webapp.usecase.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadAllTaskReturnUseCase extends SimpleReturnUseCase<List<OutputTaskDTO>> {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private DatabaseMapper databaseMapper;

    @Autowired
    public ReadAllTaskReturnUseCase(TaskRepository taskRepository, TaskMapper taskMapper, DatabaseMapper databaseMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.databaseMapper = databaseMapper;
    }

    @Override
    public List<OutputTaskDTO> execute() {
        List<DatabaseTaskDTO> dbTasks = taskRepository.findAll();

        List<Task> tasks = dbTasks.stream()
            .map(task -> {
                try {
                    return databaseMapper.createEntity(task);
                } catch (TextLengthOverLimitException | EmptyTextException e) {
                    throw new RuntimeException(e);
                }
            }).toList();

        return tasks.stream()
            .map(taskMapper::createDTO).toList();
    }
}

