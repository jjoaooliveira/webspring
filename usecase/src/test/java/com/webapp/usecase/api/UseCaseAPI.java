package com.webapp.usecase.api;

import com.webapp.usecase.annotation.DeleteAnnotationUseCase;
import com.webapp.usecase.annotation.ReadAllAnnotationUseCase;
import com.webapp.usecase.annotation.SaveAnnotationUseCase;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import com.webapp.usecase.mapper.TaskMapper;
import com.webapp.usecase.task.DeleteTaskUseCase;
import com.webapp.usecase.task.ReadAllTaskUseCase;
import com.webapp.usecase.task.SaveTaskUseCase;

import java.util.List;
import java.util.UUID;

public class UseCaseAPI {
    public UseCaseAPI() {
    }

    public List<OutputAnnotationDTO> readAllAnnotation(AnnotationMapper mapper, AnnotationDataAccess dataAccess) {
        ReadAllAnnotationUseCase readAllAnnotationUseCase = new ReadAllAnnotationUseCase(dataAccess, mapper);
        return readAllAnnotationUseCase.execute();
    }

    public OutputAnnotationDTO saveAnnotation(InputAnnotationDTO inputAnnotationDTO, AnnotationMapper mapper, AnnotationDataAccess dataAccess) {
        SaveAnnotationUseCase saveAnnotationUseCase = new SaveAnnotationUseCase(dataAccess, mapper);
        return saveAnnotationUseCase.execute(inputAnnotationDTO);
    }

    public void deleteAnnotation(UUID uuid, AnnotationDataAccess dataAccess) {
        DeleteAnnotationUseCase deleteAnnotationUseCase = new DeleteAnnotationUseCase(dataAccess);
        deleteAnnotationUseCase.execute(uuid);
    }

    public List<OutputTaskDTO> readAllTask(TaskMapper mapper, TaskDataAccess dataAccess) {
        ReadAllTaskUseCase readAllTaskUseCase = new ReadAllTaskUseCase(dataAccess, mapper);
        return readAllTaskUseCase.execute();
    }

    public OutputTaskDTO saveTask(InputTaskDTO inputTaskDTO, TaskMapper mapper, TaskDataAccess dataAccess) {
        SaveTaskUseCase saveTaskUseCase = new SaveTaskUseCase(dataAccess, mapper);
        return saveTaskUseCase.execute(inputTaskDTO);
    }

    public void deleteTask(UUID uuid, TaskDataAccess dataAccess) {
        DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(dataAccess);
        deleteTaskUseCase.execute(uuid);
    }
}
