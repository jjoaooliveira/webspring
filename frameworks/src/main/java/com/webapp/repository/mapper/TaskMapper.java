package com.webapp.repository.mapper;

import com.webapp.entity.*;
import com.webapp.repository.entity.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskMapper {
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
