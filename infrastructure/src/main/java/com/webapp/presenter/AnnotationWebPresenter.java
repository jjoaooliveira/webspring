package com.webapp.presenter;

import com.webapp.controller.AnnotationWebController;
import com.webapp.usecase.annotation.AnnotationInputData;
import com.webapp.usecase.annotation.AnnotationOutputData;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class AnnotationWebPresenter {
    public EntityModel<AnnotationOutputData> toEntityModel(AnnotationOutputData annotationOutputData) {
        return parseResponse(annotationOutputData);
    }

    public CollectionModel<EntityModel<AnnotationOutputData>> toCollectionModel(List<AnnotationOutputData> annotationOutpuDataList) {
        List<EntityModel<AnnotationOutputData>> entityModelList = annotationOutpuDataList
                .stream()
                .map(this::parseResponse)
                .toList();
        return parseResponseList(entityModelList);
    }

    private EntityModel<AnnotationOutputData> parseResponse(AnnotationOutputData annotationOutputData) {
        formatDate(annotationOutputData);

        return EntityModel.of(
                annotationOutputData,
                linkTo(WebMvcLinkBuilder.methodOn(AnnotationWebController.class)
                        .getAnnotationById(annotationOutputData.getId()))
                        .withSelfRel(),
                linkTo(WebMvcLinkBuilder.methodOn(AnnotationWebController.class)
                        .getAllAnnotation())
                        .withRel("annotations")
        );
    }

    private CollectionModel<EntityModel<AnnotationOutputData>> parseResponseList(List<EntityModel<AnnotationOutputData>> entityModelList) {
        return CollectionModel.of(
                entityModelList,
                linkTo(WebMvcLinkBuilder.methodOn(AnnotationWebController.class)
                        .getAllAnnotation())
                        .withSelfRel());
    }

    private void formatDate(AnnotationOutputData annotationOutputData) {
        OffsetDateTime convertedInstant = OffsetDateTime.parse(annotationOutputData.getCreationDate());
        String formatedDate = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").format(convertedInstant);
        annotationOutputData.setCreationDate(formatedDate);
    }
}
