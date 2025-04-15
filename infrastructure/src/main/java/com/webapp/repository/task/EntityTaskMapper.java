package com.webapp.repository;

import com.webapp.entity.*;
import com.webapp.repository.entity.TaskEntity;

import java.util.UUID;

class EntityTaskMapper {
    public TaskEntity toPersistence(Task task) {
        if(task.getId() == null) {
            return new TaskEntity(
                    UUID.randomUUID(),
                    task.getTitle(),
                    task.getContent(),
                    task.getCreation(),
                    task.getExpiration(),
                    task.isCompleted()
            );
        }

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
            new TimeMark(entity.getCreation()),
            new TimedMark(entity.getExpiration()),
            entity.getCompleted()
        );
    }
}
