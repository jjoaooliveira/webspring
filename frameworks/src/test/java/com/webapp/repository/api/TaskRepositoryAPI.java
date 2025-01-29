package com.webapp.repository.api;

import com.webapp.entity.Task;
import com.webapp.repository.TaskDataAccessImpl;
import com.webapp.repository.TaskRepository;
import com.webapp.repository.mapper.TaskPersistenceMapper;

import java.util.List;
import java.util.UUID;

public class TaskRepositoryAPI {

    private TaskRepository repository;
    private TaskPersistenceMapper mapper;

    public TaskRepositoryAPI(TaskRepository repository, TaskPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task taskDataAccessSave(Task task) {
        TaskDataAccessImpl taskDataAccess = new TaskDataAccessImpl(repository, mapper);
        return taskDataAccess.save(task);
    }

    public void taskDataAccessDelete(UUID uuid) {
        TaskDataAccessImpl taskDataAccess = new TaskDataAccessImpl(repository, mapper);
        taskDataAccess.delete(uuid);
    }

    public List<Task> taskDataAccessFindAll() {
        TaskDataAccessImpl taskDataAccess = new TaskDataAccessImpl(repository, mapper);
        return taskDataAccess.findAll();
    }
}
