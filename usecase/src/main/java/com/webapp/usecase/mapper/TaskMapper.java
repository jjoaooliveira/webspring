package com.webapp.usecase.mapper;

import com.webapp.entity.*;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public OutputTaskDTO toOutputDTO(Task task) {
        return new OutputTaskDTO(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getCreation(),
                task.getExpiration(),
                task.getTimeLeft(),
                task.isCompleted(),
                task.isExpired()
        );
    }

    public Task toTask(InputTaskDTO inputTaskDTO) {
        return new Task(
                inputTaskDTO.id(),
                new Title(inputTaskDTO.title()),
                new Content(inputTaskDTO.content()),
                new TimeMark(inputTaskDTO.creation()),
                new TimedMark(inputTaskDTO.expiration()),
                inputTaskDTO.completed()
        );
    }
}
