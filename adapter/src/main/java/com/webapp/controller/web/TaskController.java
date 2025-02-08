package com.webapp.controller.web;

import com.webapp.presenter.TaskPresenter;
import com.webapp.usecase.SimpleInputUseCase;
import com.webapp.usecase.SimpleReturnUseCase;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private UseCase<InputTaskDTO, OutputTaskDTO> saveUseCase;
    private SimpleReturnUseCase<List<OutputTaskDTO>> findAllUseCase;
    private UseCase<UUID, OutputTaskDTO> findByIdUseCase;
    private UseCase<String, List<OutputTaskDTO>> findByTitleUseCase;
    private SimpleInputUseCase<UUID> deleteUseCase;
    private TaskPresenter presenter;

    @Autowired
    public TaskController(
            UseCase<InputTaskDTO, OutputTaskDTO> saveUseCase,
            SimpleReturnUseCase<List<OutputTaskDTO>> findAllUseCase,
            UseCase<UUID, OutputTaskDTO> findByIdUseCase,
            UseCase<String, List<OutputTaskDTO>> findByTitleUseCase,
            SimpleInputUseCase<UUID> deleteUseCase,
            TaskPresenter presenter
    ) {
        this.saveUseCase = saveUseCase;
        this.findAllUseCase = findAllUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findByTitleUseCase = findByTitleUseCase;
        this.deleteUseCase = deleteUseCase;
        this.presenter = presenter;
    }

    @GetMapping("/{id}")
    public EntityModel<OutputTaskDTO> getTaskById(@PathVariable("id") UUID uuid) {
        OutputTaskDTO taskDTO = findByIdUseCase.execute(uuid);
        return presenter.toEntityModel(taskDTO);
    }

    @GetMapping("/{title}")
    public CollectionModel<EntityModel<OutputTaskDTO>> getTaskByTitle(@PathVariable("title") String title) {
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
    public EntityModel<OutputTaskDTO> postTask(InputTaskDTO inputTaskDTO) {
        OutputTaskDTO outputTaskDTO = saveUseCase.execute(inputTaskDTO);
        return presenter.toEntityModel(outputTaskDTO);
    }

    @PutMapping
    public EntityModel<OutputTaskDTO> putTask(InputTaskDTO inputTaskDTO) {
        OutputTaskDTO outputTaskDTO = saveUseCase.execute(inputTaskDTO);
        return presenter.toEntityModel(outputTaskDTO);
    }

    @DeleteMapping
    public void deleteTask(UUID uuid) {
        deleteUseCase.execute(uuid);
    }
}
