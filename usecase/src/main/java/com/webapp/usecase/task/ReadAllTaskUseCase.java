package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.usecase.SimpleReturnUseCase;
import com.webapp.usecase.data_access.TaskRepository;

import java.util.List;

import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadAllTaskUseCase extends SimpleReturnUseCase<List<OutputTaskDTO>> {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    @Autowired
    public ReadAllTaskUseCase(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<OutputTaskDTO> execute() {
        List<Task> persistedTasks = taskRepository.findAll();

        return persistedTasks.stream()
            .map(taskMapper::toOutputDTO).toList();
    }
}

