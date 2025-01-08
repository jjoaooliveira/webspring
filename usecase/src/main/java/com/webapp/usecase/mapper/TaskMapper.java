package com.webapp.usecase.mapper;

import com.webapp.entity.Content;
import com.webapp.entity.Task;
import com.webapp.entity.Title;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dto.task.NewTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.dto.task.TaskDTO;

import java.time.LocalDateTime;

public class TaskMapper {
    public OutputTaskDTO toOutputDTO(Task task) {
        String creationDate = task.getCreationDate().toLocalDate().toString();
        String creationTime = task.getCreationDate().toLocalTime().toString();
        String expirationDate = task.getExpirationDate().toLocalDate().toString();
        String expirationTime = task.getExpirationDate().toLocalTime().toString();
        
        return new OutputTaskDTO(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                creationDate,
                creationTime,
                expirationDate,
                expirationTime,
                task.getTimeLeft(),
                task.isCompleted(),
                task.isExpired()
        );
    }

    public TaskDTO toTaskDTO(Task task) {
        return new TaskDTO(
            task.getId(),
            task.getTitle(),
            task.getContent(),
            task.getCreationDate().toString(),
            task.getExpirationDate().toString(),
            task.isCompleted()
        );
    }

    public Task toTask(TaskDTO taskDTO) throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title(taskDTO.title());
        Content content = Content.create(taskDTO.content());
        LocalDateTime expirationDate = LocalDateTime.parse(taskDTO.expirationDate());

        return new Task(
                title,
                content,
                expirationDate
        );
    }

    public Task toNewTask(NewTaskDTO newTaskDTO) throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title(newTaskDTO.title());
        Content content = Content.create(newTaskDTO.content());
        LocalDateTime expirationDate = LocalDateTime.parse(newTaskDTO.expirationDate());

        return new Task(
                title,
                content,
                expirationDate
        );
    }


}
