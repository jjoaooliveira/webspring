package com.webapp.repository.task;

import com.webapp.entity.Task;
import com.webapp.usecase.task.TaskDataAccess;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class TaskDataAccessImpl implements TaskDataAccess {
    private TaskRepository repository;
    private final EntityTaskMapper mapper;

    public TaskDataAccessImpl() {
        this.mapper = new EntityTaskMapper();
    }

    @Override
    public List<Task> findByTitle(String title) {
        List<TaskEntity> taskEntityList = repository.findByTitle(title);
        return taskEntityList.stream().map(mapper::toTask).toList();
    }

    @Override
    public Task findById(UUID uuid) {
        Optional<TaskEntity> taskEntity = repository.findById(uuid);
        return mapper.toTask(taskEntity
                .orElseThrow(() -> new EntityNotFoundException("Task does not exist with given id"))
        );
    }

    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = mapper.toPersistence(task);
        TaskEntity persistedTask = repository.save(taskEntity);
        return mapper.toTask(persistedTask);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Task> findAll() {
        List<TaskEntity> taskEntityList = repository.findAll();
        return taskEntityList.stream()
                .map(mapper::toTask)
                .toList();
    }

    public void setRepository(TaskRepository repository) {
        this.repository = repository;
    }
}
