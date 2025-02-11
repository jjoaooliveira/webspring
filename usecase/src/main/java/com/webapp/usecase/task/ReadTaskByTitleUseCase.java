package com.webapp.usecase.task;

import com.webapp.entity.Task;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadTaskByTitleUseCase extends UseCase<String, List<OutputTaskDTO>> {
    private TaskDataAccess taskDataAccess;
    private TaskMapper taskMapper;

    @Autowired
    public ReadTaskByTitleUseCase(TaskDataAccess taskDataAccess, TaskMapper taskMapper) {
        this.taskDataAccess = taskDataAccess;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<OutputTaskDTO> execute(String title) {
        List<Task> taskList = taskDataAccess.findByTitle(title);
        return taskList.stream()
                .map(taskMapper::toOutputDTO)
                .toList();
    }
}
