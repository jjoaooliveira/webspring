package com.webapp.presenter;

import com.webapp.controller.web.AnnotationController;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AnnotationPresenter {
    public EntityModel<OutputAnnotationDTO> toEntityModel(OutputAnnotationDTO outputAnnotationDTO) {
        return EntityModel.of(
                outputAnnotationDTO,
                linkTo(WebMvcLinkBuilder.methodOn(AnnotationController.class)
                        .getAnnotationById(outputAnnotationDTO.id()))
                        .withSelfRel(),
                linkTo(WebMvcLinkBuilder.methodOn(AnnotationController.class)
                        .getAllAnnotation())
                        .withRel("annotations"));
    }

    public CollectionModel<EntityModel<OutputAnnotationDTO>> toCollectionModel(Collection<EntityModel<OutputAnnotationDTO>> collection) {
        return CollectionModel.of(
                collection,
                linkTo(WebMvcLinkBuilder.methodOn(AnnotationController.class)
                        .getAllAnnotation())
                        .withSelfRel());
    }
}
