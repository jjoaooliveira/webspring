package com.webapp.usecase.dataaccess;

import com.webapp.usecase.dto.task.TaskDTO;

import java.util.List;

public interface TaskRepository {
    TaskDTO save(TaskDTO dbTaskDTO);
    void delete(Long id);
    List<TaskDTO> findAll();
}
