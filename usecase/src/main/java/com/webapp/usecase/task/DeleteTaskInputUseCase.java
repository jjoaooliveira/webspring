package com.webapp.usecase.task;

import com.webapp.usecase.SimpleUseCase;
import com.webapp.usecase.dataaccess.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteTaskUseCase extends SimpleUseCase<Long> {
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
