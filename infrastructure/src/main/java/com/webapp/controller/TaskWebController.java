package com.webapp.controller;

import com.webapp.presenter.TaskWebPresenter;
import com.webapp.usecase.task.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskWebController {
    private final AbstractTaskInteractorFactory factory;
    private final TaskWebPresenter presenter;

    @Autowired
    public TaskWebController(AbstractTaskInteractorFactory factory, TaskWebPresenter presenter) {
        this.factory = factory;
        this.presenter = presenter;
    }

    @GetMapping("/{id}")
    public EntityModel<TaskOutputData> getTaskById(@PathVariable("id") UUID uuid) {
        IReadTaskByIdUseCase readTaskByIdUseCase = factory.makeReadTaskByIdUseCase();
        TaskOutputData taskOutputData = readTaskByIdUseCase.execute(uuid);

        return presenter.toEntityModel(taskOutputData);
    }

    @GetMapping("/title")
    public CollectionModel<EntityModel<TaskOutputData>> getTaskByTitle(@RequestParam("title") String title) {
        IReadTaskByTitleUseCase readTaskByTitleUseCase = factory.makeReadTaskByTitleUseCase();
        List<TaskOutputData> taskOutputDataList = readTaskByTitleUseCase.execute(title);

        return presenter.toCollectionModel(taskOutputDataList);
    }

    @GetMapping
    public CollectionModel<EntityModel<TaskOutputData>> getAllTask() {
        IReadAllTaskUseCase readAllTaskUseCase = factory.makeReadAllTaskUseCase();
        List<TaskOutputData> taskOutputDataList = readAllTaskUseCase.execute();

        return presenter.toCollectionModel(taskOutputDataList);
    }

    @PostMapping
    public EntityModel<TaskOutputData> postTask(@RequestBody TaskInputData request) {
        ISaveTaskUseCase saveTaskUseCase = factory.makeSaveTaskUseCase();
        TaskOutputData taskOutputData = saveTaskUseCase.execute(request);

        return presenter.toEntityModel(taskOutputData);
    }

    @PutMapping
    public EntityModel<TaskOutputData> putTask(@RequestBody TaskInputData request) {
        IUpdateTaskUseCase updateTaskUseCase = factory.makeUpdateTaskUseCase();
        TaskOutputData taskOutputData = updateTaskUseCase.execute(request);

        return presenter.toEntityModel(taskOutputData);
    }

    @DeleteMapping
    public void deleteTask(@RequestParam("id") UUID uuid) {
        IDeleteTaskUseCase deleteTaskUseCase = factory.makeDeleteTaskUseCase();
        deleteTaskUseCase.execute(uuid);
    }
}
