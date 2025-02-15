package com.webapp.controller.web;

import com.webapp.presenter.AnnotationPresenter;
import com.webapp.usecase.*;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/annotation")
public class AnnotationController {
    private ReadAllUseCase<List<OutputAnnotationDTO>> findAllUseCase;
    private ReadByUseCase<UUID, OutputAnnotationDTO> findByIdUseCase;
    private ReadByUseCase<String, List<OutputAnnotationDTO>> findByTitleUseCase;
    private SaveUseCase<InputAnnotationDTO, OutputAnnotationDTO> saveUsecase;
    private DeleteUseCase deleteUseCase;
    private UpdateUseCase<InputAnnotationDTO, OutputAnnotationDTO> updateUseCase;
    private AnnotationPresenter presenter;

    @Autowired
    public AnnotationController(
            ReadAllUseCase<List<OutputAnnotationDTO>> findAllUseCase,
            ReadByUseCase<UUID, OutputAnnotationDTO> findByIdUseCase,
            ReadByUseCase<String, List<OutputAnnotationDTO>> findByTitleUseCase,
            SaveUseCase<InputAnnotationDTO, OutputAnnotationDTO> saveUsecase,
            @Qualifier("annotation") DeleteUseCase deleteUseCase,
            UpdateUseCase<InputAnnotationDTO, OutputAnnotationDTO> updateUseCase,
            AnnotationPresenter presenter
    ) {
        this.findAllUseCase = findAllUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findByTitleUseCase = findByTitleUseCase;
        this.saveUsecase = saveUsecase;
        this.deleteUseCase = deleteUseCase;
        this.updateUseCase = updateUseCase;
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

    @GetMapping("/title")
    public CollectionModel<EntityModel<OutputAnnotationDTO>> getAnnotationByTitle(@RequestParam("title") String title) {
        List<OutputAnnotationDTO> annotationDTOs = findByTitleUseCase.execute(title);
        List<EntityModel<OutputAnnotationDTO>> annotationModelList = annotationDTOs.stream()
                .map(presenter::toEntityModel)
                .toList();
        return presenter.toCollectionModel(annotationModelList);
    }

    @PostMapping
    public EntityModel<OutputAnnotationDTO> postAnnotation(@RequestBody InputAnnotationDTO inputAnnotationDTO) {
        OutputAnnotationDTO annotationDTO = saveUsecase.execute(inputAnnotationDTO);
        return presenter.toEntityModel(annotationDTO);
    }

    @PutMapping
    public EntityModel<OutputAnnotationDTO> putAnnotation(@RequestBody InputAnnotationDTO inputAnnotationDTO) {
        OutputAnnotationDTO annotationDTO = updateUseCase.execute(inputAnnotationDTO);
        return presenter.toEntityModel(annotationDTO);
    }

    @DeleteMapping
    public void deleteAnnotation(UUID uuid) {
        deleteUseCase.execute(uuid);
    }
}
