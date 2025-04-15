package com.webapp.controller;

import com.webapp.presenter.TaskPresenter;
import com.webapp.usecase.*;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private SaveUseCase<InputTaskDTO, OutputTaskDTO> saveUseCase;
    private ReadAllUseCase<List<OutputTaskDTO>> findAllUseCase;
    private ReadByUseCase<UUID, OutputTaskDTO> findByIdUseCase;
    private ReadByUseCase<String, List<OutputTaskDTO>> findByTitleUseCase;
    private DeleteUseCase deleteUseCase;
    private UpdateUseCase<InputTaskDTO, OutputTaskDTO> upadteUseCase;
    private TaskPresenter presenter;

    @Autowired
    public TaskController(
            SaveUseCase<InputTaskDTO, OutputTaskDTO> saveUseCase,
            ReadAllUseCase<List<OutputTaskDTO>> findAllUseCase,
            ReadByUseCase<UUID, OutputTaskDTO> findByIdUseCase,
            ReadByUseCase<String, List<OutputTaskDTO>> findByTitleUseCase,
            @Qualifier("task") DeleteUseCase deleteUseCase,
            UpdateUseCase<InputTaskDTO, OutputTaskDTO> updateUseCase,
            TaskPresenter presenter
    ) {
        this.saveUseCase = saveUseCase;
        this.findAllUseCase = findAllUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findByTitleUseCase = findByTitleUseCase;
        this.deleteUseCase = deleteUseCase;
        this.upadteUseCase = updateUseCase;
        this.presenter = presenter;
    }

    @GetMapping("/{id}")
    public EntityModel<OutputTaskDTO> getTaskById(@PathVariable("id") UUID uuid) {
        OutputTaskDTO taskDTO = findByIdUseCase.execute(uuid);
        return presenter.toEntityModel(taskDTO);
    }

    @GetMapping("/title")
    public CollectionModel<EntityModel<OutputTaskDTO>> getTaskByTitle(@RequestParam("title") String title) {
        List<OutputTaskDTO> taskDTO = findByTitleUseCase.execute(title);
        List<EntityModel<OutputTaskDTO>> taskModelList = taskDTO.stream()
                .map(presenter::toEntityModel)
                .toList();
        return presenter.toCollectionModel(taskModelList);
    }

    @GetMapping
    public CollectionModel<EntityModel<OutputTaskDTO>> getAllTask() {
        List<OutputTaskDTO> taskDTOList = findAllUseCase.execute();
        List<EntityModel<OutputTaskDTO>> taskModelList = taskDTOList.stream()
                .map(presenter::toEntityModel).toList();
        return presenter.toCollectionModel(taskModelList);
    }

    @PostMapping
    public EntityModel<OutputTaskDTO> postTask(@RequestBody InputTaskDTO inputTaskDTO) {
        OutputTaskDTO outputTaskDTO = saveUseCase.execute(inputTaskDTO);
        return presenter.toEntityModel(outputTaskDTO);
    }

    @PutMapping
    public EntityModel<OutputTaskDTO> putTask(@RequestBody InputTaskDTO inputTaskDTO) {
        OutputTaskDTO outputTaskDTO = upadteUseCase.execute(inputTaskDTO);
        return presenter.toEntityModel(outputTaskDTO);
    }

    @DeleteMapping
    public void deleteTask(UUID uuid) {
        deleteUseCase.execute(uuid);
    }
}
