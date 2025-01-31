package com.webapp.entity.api;

import com.webapp.entity.*;

import java.time.OffsetDateTime;
import java.util.UUID;

public class TaskAPI {
    private Task task;

    /**
     * @param id the id of the Task
     * @param title the title of the Task
     * @param content the content of the Task
     * @param timeMark the time mark of the Task
     * @param timedMark the timed mark of the Task
     * @param completed defines if the task is already completed or not
     * @return a new Task if all the params is correct
     */
    public TaskAPI(
            UUID id,
            String title,
            String content,
            OffsetDateTime timeMark,
            OffsetDateTime timedMark,
            Boolean completed
    ){
        this.task = new Task(
                id,
                new Title(title),
                new Content(content),
                new TimeMark(timeMark),
                new TimedMark(timedMark),
                completed
        );
    }

    /**
     * @param title the title of the Task
     * @param content the content of the Task
     * @param timeMark the time mark of the Task
     * @param timedMark the timed mark of the Task
     * @param completed defines if the task is already completed or not
     * @return a new Task if all the params is correct
     */
    public TaskAPI(
            String title,
            String content,
            OffsetDateTime timeMark,
            OffsetDateTime timedMark,
            Boolean completed
    ){
        this.task = new Task(
                new Title(title),
                new Content(content),
                new TimeMark(timeMark),
                new TimedMark(timedMark),
                completed);
    }

    public UUID getId() {
        return task.getId();
    }

    public String getContent() {
        return task.getContent();
    }

    public String getTitle() {
        return task.getTitle();
    }

    public OffsetDateTime getCreation() {
        return task.getCreation();
    }

    public OffsetDateTime getExpiration() {
        return task.getExpiration();
    }

    public Boolean taskIsCompleted() {
        return task.isCompleted();
    }
}
