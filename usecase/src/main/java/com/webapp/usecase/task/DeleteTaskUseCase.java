package com.webapp.usecase.task;

import com.webapp.usecase.SimpleInputUseCase;
import com.webapp.usecase.data_access.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class DeleteTaskUseCase extends SimpleInputUseCase<String> {
    private TaskRepository taskRepository;

    @Autowired
    public DeleteTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void execute(Long aLong) {
        taskRepository.delete(aLong);
    }
}
