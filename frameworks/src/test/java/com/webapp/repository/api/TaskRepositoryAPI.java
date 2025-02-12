package com.webapp.repository.api;

import com.webapp.entity.Task;
import com.webapp.repository.TaskDataAccessImpl;
import com.webapp.repository.TaskRepository;
import com.webapp.repository.mapper.EntityTaskMapper;

import java.util.List;
import java.util.UUID;

public class TaskRepositoryAPI {

    private TaskDataAccessImpl taskDataAccess;

    public TaskRepositoryAPI(TaskRepository repository, EntityTaskMapper mapper) {
        this.taskDataAccess = new TaskDataAccessImpl(repository, mapper);
    }

    public Task taskDataAccessSave(Task task) {
        return taskDataAccess.save(task);
    }

    public List<Task> taskDataAccessFindByTitle(String title) {
        return taskDataAccess.findByTitle(title);
    }

    public Task taskDataAccessFindById(UUID uuid) {
        return taskDataAccess.findById(uuid);
    }

    public List<Task> taskDataAccessFindAll() {
        return taskDataAccess.findAll();
    }

    public void taskDataAccessDelete(UUID uuid) {
        taskDataAccess.delete(uuid);
    }
}
