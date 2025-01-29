package com.webapp.repository.mapper;

import com.webapp.entity.*;
import com.webapp.repository.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskPersistenceMapper {
    public TaskEntity toPersistence(Task task) {
        TaskEntity taskEntity = new TaskEntity();

        if(task.getId() != null) taskEntity.setUUID(task.getId());
        taskEntity.setTitle(task.getTitle());
        taskEntity.setContent(task.getContent());
        taskEntity.setCreation(task.getCreation());
        taskEntity.setExpiration(task.getExpiration());
        taskEntity.setCompleted(task.isCompleted());

        return taskEntity;
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
