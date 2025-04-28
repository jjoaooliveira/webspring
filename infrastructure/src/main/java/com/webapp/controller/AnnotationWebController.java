package com.webapp.controller;

import com.webapp.presenter.AnnotationWebPresenter;
import com.webapp.usecase.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/annotation")
public class AnnotationWebController {
    private final AbstractAnnotationInteractorFactory factory;
    private final AnnotationWebPresenter presenter;

    public AnnotationWebController(AbstractAnnotationInteractorFactory factory, AnnotationWebPresenter presenter) {
        this.factory = factory;
        this.presenter = presenter;
    }

    @GetMapping
    public CollectionModel<EntityModel<AnnotationOutputData>> getAllAnnotation() {
        IReadAllAnnotationUseCase readAllAnnotationUseCase = factory.makeReadAllInteractor();
        List<AnnotationOutputData> annotationOutputList = readAllAnnotationUseCase.execute();
        return presenter.toCollectionModel(annotationOutputList);
    }

    @GetMapping("/{id}")
    public EntityModel<AnnotationOutputData> getAnnotationById(@PathVariable("id") UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        IReadAnnotationByIdUseCase readAnnotationByIdUseCase = factory.makeReadByIdInteractor();
        AnnotationOutputData annotationOutputData = readAnnotationByIdUseCase.execute(uuid);
        return presenter.toEntityModel(annotationOutputData);
    }

    @GetMapping("/title")
    public CollectionModel<EntityModel<AnnotationOutputData>> getAnnotationByTitle(@RequestParam String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("The title parameter cannot be null or empty.");
        }
        IReadAnnotationByTitleUseCase readAnnotationByTitleUseCase = factory.makeReadByTitleInteractor();
        List<AnnotationOutputData> annotationOutputDataList = readAnnotationByTitleUseCase.execute(title);
        return presenter.toCollectionModel(annotationOutputDataList);
    }

    @PostMapping
    public EntityModel<AnnotationOutputData> postAnnotation(@RequestBody AnnotationInputData saveAnnotationInputData) {
        ISaveAnnotationUseCase saveAnnotationUseCase = factory.makeSaveInteractor();
        AnnotationOutputData annotationOutputData = saveAnnotationUseCase.execute(saveAnnotationInputData);
        return presenter.toEntityModel(annotationOutputData);
    }

    @PutMapping
    public EntityModel<AnnotationOutputData> putAnnotation(@RequestBody AnnotationInputData updateAnnotationInputData) {
        if(updateAnnotationInputData.id() == null) {
            throw new NullPointerException("UUID cannot be null");
        }
        if(updateAnnotationInputData.title() == null || updateAnnotationInputData.title().isBlank()) {
            throw new IllegalArgumentException("The title cannot be empty or null");
        }
        if(updateAnnotationInputData.content() == null || updateAnnotationInputData.content().isBlank()) {
            throw new IllegalArgumentException("The content cannot be empty or null");
        }
        IUpdateAnnotationUseCase updateAnnotationUseCase = factory.makeUpdateInteractor();
        AnnotationOutputData annotationOutputData = updateAnnotationUseCase.execute(updateAnnotationInputData);
        return presenter.toEntityModel(annotationOutputData);
    }

    @DeleteMapping
    public void deleteAnnotation(@RequestParam("id") UUID uuid) {
        if(uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        IDeleteAnnotationUseCase deleteAnnotationUseCase = factory.makeDeleteInteractor();
        deleteAnnotationUseCase.execute(uuid);
    }
}
