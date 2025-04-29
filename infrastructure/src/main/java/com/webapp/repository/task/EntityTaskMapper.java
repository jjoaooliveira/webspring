package com.webapp.repository.task;

import com.webapp.entity.*;
import org.springframework.stereotype.Component;

@Component
class EntityTaskMapper {
    public TaskEntity toPersistence(Task task) {
        return new TaskEntity(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getCreation(),
                task.getExpiration(),
                task.isCompleted()
        );
    }

    public Task toTask(TaskEntity entity) {
        return new Task(
            entity.getUUID(),
            new Title(entity.getTitle()),
            new Content(entity.getContent()),
            entity.getCreation(),
            entity.getExpiration(),
            entity.getCompleted()
        );
    }
}
