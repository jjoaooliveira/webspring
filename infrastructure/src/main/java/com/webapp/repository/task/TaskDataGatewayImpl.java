package com.webapp.repository.task;

import com.webapp.entity.Task;
import com.webapp.usecase.task.TaskDataGateway;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
class TaskDataGatewayImpl implements TaskDataGateway {
    private final TaskRepository repository;
    private final EntityTaskMapper mapper;

    @Autowired
    public TaskDataGatewayImpl(TaskRepository repository, EntityTaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
}
