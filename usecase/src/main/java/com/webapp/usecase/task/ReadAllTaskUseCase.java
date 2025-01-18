package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.usecase.SimpleReturnUseCase;
import com.webapp.usecase.dataaccess.TaskRepository;

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
        List<TaskDTO> dbTasks = taskRepository.findAll();
        List<Task> tasks = dbTasks.stream()
            .map(task -> {
                try {
                    return taskMapper.toTask(task);
                } catch (TextLengthOverLimitException | EmptyTextException e) {
                    throw new RuntimeException(e);
                }
            }).toList();

        return tasks.stream()
            .map(taskMapper::toOutputDTO).toList();
    }
}

