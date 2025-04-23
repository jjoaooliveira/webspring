package com.webapp.usecase.task;

import com.webapp.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskDataGateway {
    List<Task> findByTitle(String title);
    Task findById(UUID uuid);
    Task save(Task task);
    void delete(UUID id);
    List<Task> findAll();
}
