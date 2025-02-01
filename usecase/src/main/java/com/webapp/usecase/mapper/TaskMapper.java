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
        return inputTaskDTO.id()
                .map(uuid ->
                    new Task(
                        uuid,
                        new Title(inputTaskDTO.title()),
                        new Content(inputTaskDTO.content()),
                        new TimeMark(),
                        new TimedMark(inputTaskDTO.expirationDate()),
                        inputTaskDTO.completed()
                    )
                ).orElseGet(() ->
                    new Task(
                        new Title(inputTaskDTO.title()),
                        new Content(inputTaskDTO.content()),
                        new TimeMark(),
                        new TimedMark(inputTaskDTO.expirationDate())
                    )
                );
    }


}
