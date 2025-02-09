package com.webapp.controller.web;

import com.webapp.presenter.AnnotationPresenter;
import com.webapp.usecase.SimpleInputUseCase;
import com.webapp.usecase.SimpleReturnUseCase;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/annotation")
public class AnnotationController {
    private SimpleReturnUseCase<List<OutputAnnotationDTO>> findAllUseCase;
    private UseCase<UUID, OutputAnnotationDTO> findByIdUseCase;
    private UseCase<String, List<OutputAnnotationDTO>> findByTitleUseCase;
    private UseCase<InputAnnotationDTO, OutputAnnotationDTO> saveUsecase;
    private SimpleInputUseCase<UUID> deleteUseCase;
    private AnnotationPresenter presenter;

    @Autowired
    public AnnotationController(
            SimpleReturnUseCase<List<OutputAnnotationDTO>> findAllUseCase,
            UseCase<UUID, OutputAnnotationDTO> findByIdUseCase,
            UseCase<String, List<OutputAnnotationDTO>> findByTitleUseCase,
            UseCase<InputAnnotationDTO, OutputAnnotationDTO> saveUsecase,
            SimpleInputUseCase<UUID> deleteUseCase,
            AnnotationPresenter presenter
    ) {
        this.findAllUseCase = findAllUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findByTitleUseCase = findByTitleUseCase;
        this.saveUsecase = saveUsecase;
        this.deleteUseCase = deleteUseCase;
        this.presenter = presenter;
    }

    @GetMapping
    public CollectionModel<EntityModel<OutputAnnotationDTO>> getAllAnnotation() {
        List<OutputAnnotationDTO> annotationDTOList = findAllUseCase.execute();
        List<EntityModel<OutputAnnotationDTO>> annotationModelList = annotationDTOList.stream()
                .map(presenter::toEntityModel)
                .toList();
        return presenter.toCollectionModel(annotationModelList);
    }

    @GetMapping("/{id}")
    public EntityModel<OutputAnnotationDTO> getAnnotationById(@PathVariable("id") UUID uuid) {
        OutputAnnotationDTO annotationDTOs = findByIdUseCase.execute(uuid);
        return presenter.toEntityModel(annotationDTOs);
    }

    @GetMapping("/{title}")
    public CollectionModel<EntityModel<OutputAnnotationDTO>> getAnnotationByTitle(@PathVariable("title") String title) {
        List<OutputAnnotationDTO> annotationDTOs = findByTitleUseCase.execute(title);
        List<EntityModel<OutputAnnotationDTO>> annotationModelList = annotationDTOs.stream()
                .map(presenter::toEntityModel)
                .toList();
        return presenter.toCollectionModel(annotationModelList);
    }

    @PostMapping
    public EntityModel<OutputAnnotationDTO> postAnnotation(InputAnnotationDTO inputAnnotationDTO) {
        OutputAnnotationDTO annotationDTO = saveUsecase.execute(inputAnnotationDTO);
        return presenter.toEntityModel(annotationDTO);
    }

    @PutMapping
    public EntityModel<OutputAnnotationDTO> putAnnotation(InputAnnotationDTO inputAnnotationDTO) {
        OutputAnnotationDTO annotationDTO = saveUsecase.execute(inputAnnotationDTO);
        return presenter.toEntityModel(annotationDTO);
    }

    @DeleteMapping
    public void deleteAnnotation(UUID uuid) {
        deleteUseCase.execute(uuid);
    }
}
