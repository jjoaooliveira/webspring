package com.webapp.repository;

import com.webapp.entity.Task;
import com.webapp.repository.entity.TaskEntity;
import com.webapp.repository.mapper.TaskPersistenceMapper;
import com.webapp.usecase.data_access.TaskDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TaskDataAccessImpl implements TaskDataAccess {
    private TaskRepository taskRepository;
    private TaskPersistenceMapper persistenceMapper;

    @Autowired
    public TaskDataAccessImpl(TaskRepository taskRepository, TaskPersistenceMapper persistenceMapper) {
        this.taskRepository = taskRepository;
        this.persistenceMapper = persistenceMapper;
    }

    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = persistenceMapper.toPersistence(task);
        TaskEntity persistedTask = taskRepository.save(taskEntity);
        return persistenceMapper.toTask(persistedTask);
    }

    @Override
    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findAll() {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        return taskEntityList.stream()
                .map(persistenceMapper::toTask)
                .toList();
    }
}
