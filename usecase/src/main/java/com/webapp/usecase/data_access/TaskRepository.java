package com.webapp.usecase.data_access;

import com.webapp.entity.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);
    void delete(String id);
    List<Task> findAll();
}
