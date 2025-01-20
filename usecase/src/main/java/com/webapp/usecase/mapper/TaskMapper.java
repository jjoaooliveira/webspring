package com.webapp.usecase.mapper;

import com.webapp.entity.*;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

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

    public Task toTask(InputTaskDTO inputTaskDTO) throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title(inputTaskDTO.title());
        Content content = new Content(inputTaskDTO.content());
        TimeMark timeMark = new TimeMark();

        ZonedDateTime zonedDateTime = inputTaskDTO.expirationDate().toZonedDateTime();

        TimedMark timedMark = new TimedMark(zonedDateTime);

        return new Task(
                title,
                content,
                timeMark,
                timedMark
        );
    }


}
