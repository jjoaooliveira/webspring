package com.webapp.controller;

import com.webapp.presenter.AnnotationPresenter;
import com.webapp.usecase.annotation.AnnotationInputData;
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

    @Autowired
    public AnnotationController(
            ReadAllUseCase<List<>> findAllUseCase,
            ReadByTitleUseCase<UUID, Response> findByIdUseCase,
            ReadByTitleUseCase<String, List<Response>> findByTitleUseCase,
            SaveUseCase<AnnotationInputData, Response> saveUsecase,
            @Qualifier("annotation") DeleteUseCase deleteUseCase,
            UpdateUseCase<AnnotationInputData, Response> updateUseCase,
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
    public CollectionModel<EntityModel<Response>> getAllAnnotation() {
        List<Response> annotationDTOList = findAllUseCase.execute();
        List<EntityModel<Response>> annotationModelList = annotationDTOList.stream()
                .map(presenter::toEntityModel)
                .toList();
        return presenter.toCollectionModel(annotationModelList);
    }

    @GetMapping("/{uuid}")
    public EntityModel<Response> getAnnotationById(@PathVariable("id") UUID uuid) {
        Response annotationDTOs = findByIdUseCase.execute(uuid);
        return presenter.toEntityModel(annotationDTOs);
    }

    @GetMapping("/title")
    public CollectionModel<EntityModel<Response>> getAnnotationByTitle(@RequestParam("title") String title) {
        List<Response> annotationDTOs = findByTitleUseCase.execute(title);
        List<EntityModel<Response>> annotationModelList = annotationDTOs.stream()
                .map(presenter::toEntityModel)
                .toList();
        return presenter.toCollectionModel(annotationModelList);
    }

    @PostMapping
    public EntityModel<Response> postAnnotation(@RequestBody AnnotationInputData newAnnotationInputData) {
        Response annotationDTO = saveUsecase.execute(newAnnotationInputData);
        return presenter.toEntityModel(annotationDTO);
    }

    @PutMapping
    public EntityModel<Response> putAnnotation(@RequestBody AnnotationInputData newAnnotationInputData) {
        Response annotationDTO = updateUseCase.execute(newAnnotationInputData);
        return presenter.toEntityModel(annotationDTO);
    }

    @DeleteMapping
    public void deleteAnnotation(UUID uuid) {
        deleteUseCase.execute(uuid);
    }
}
