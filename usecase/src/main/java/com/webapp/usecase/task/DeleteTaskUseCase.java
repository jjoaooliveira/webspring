package com.webapp.usecase.task;

import com.webapp.usecase.SimpleInputUseCase;
import com.webapp.usecase.dataaccess.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteTaskUseCase extends SimpleInputUseCase<Long> {
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
