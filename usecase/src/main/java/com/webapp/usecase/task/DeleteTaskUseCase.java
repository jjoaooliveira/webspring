package com.webapp.usecase.task;

import com.webapp.usecase.DeleteUseCase;
import com.webapp.usecase.data_access.TaskDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Qualifier("task")
public class DeleteTaskUseCase extends DeleteUseCase {
    private TaskDataAccess taskDataAccess;

    @Autowired
    public DeleteTaskUseCase(TaskDataAccess taskDataAccess) {
        this.taskDataAccess = taskDataAccess;
    }

    @Override
    public void execute(UUID id) {
        Objects.requireNonNull(id);
        taskDataAccess.delete(id);
    }
}
