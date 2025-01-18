package com.webapp.usecase.mapper;

import com.webapp.entity.*;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;

public class TaskMapper {
    public OutputTaskDTO toOutputDTO(Task task) {
        
        return new OutputTaskDTO(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getCreationDate(),
                task.getCreationTime(),
                task.getExpirationDate(),
                task.getExpirationTime(),
                task.getTimeLeft(),
                task.isCompleted(),
                task.isExpired()
        );
    }

    public Task toTask(InputTaskDTO inputTaskDTO) throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title(inputTaskDTO.title());
        Content content = new Content(inputTaskDTO.content());
        TimeMark timeMark = new TimeMark();
        TimedMark timedMark = new TimedMark(inputTaskDTO.expirationDate(), inputTaskDTO.zone());

        if(inputTaskDTO.id().isPresent() && inputTaskDTO.completed().isPresent()) {
            return new Task(
                    inputTaskDTO.id().get(),
                    title,
                    content,
                    timeMark,
                    timedMark,
                    inputTaskDTO.completed().get()
            );
        }
        return new Task(
                title,
                content,
                timeMark,
                timedMark
        );
    }


}
