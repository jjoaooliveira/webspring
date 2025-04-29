package com.webapp.presenter;

import com.webapp.controller.TaskWebController;
import com.webapp.usecase.task.TaskOutputData;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class TaskWebPresenter {
    public EntityModel<TaskOutputData> toEntityModel(TaskOutputData response) {
        return parseResponse(response);
    }

    public CollectionModel<EntityModel<TaskOutputData>> toCollectionModel(List<TaskOutputData> taskOutputDataList) {
        List<EntityModel<TaskOutputData>> entityModelList = taskOutputDataList
                .stream()
                .map(this::toEntityModel)
                .toList();
        return parseResponseList(entityModelList);
    }

    private EntityModel<TaskOutputData> parseResponse(TaskOutputData taskOutputData) {
        formatDate(taskOutputData);

        return EntityModel.of(
                taskOutputData,
                linkTo(WebMvcLinkBuilder.methodOn(TaskWebController.class)
                        .getTaskById(taskOutputData.getId()))
                        .withSelfRel(),
                linkTo(WebMvcLinkBuilder.methodOn(TaskWebController.class)
                        .getAllTask())
                        .withRel("tasks")
        );
    }

    private CollectionModel<EntityModel<TaskOutputData>> parseResponseList(List<EntityModel<TaskOutputData>> entityModelList) {
        return CollectionModel.of(
                entityModelList,
                linkTo(WebMvcLinkBuilder.methodOn(TaskWebController.class)
                        .getAllTask())
                        .withSelfRel()
        );
    }

    private void formatDate(TaskOutputData taskOutputData) {
        OffsetDateTime convertedInstant = OffsetDateTime.parse(taskOutputData.getCreationDate());
        String formatedDate = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").format(convertedInstant);
        taskOutputData.setCreationDate(formatedDate);

        convertedInstant = OffsetDateTime.parse(taskOutputData.getExpirationDate());
        formatedDate = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").format(convertedInstant);
        taskOutputData.setExpirationDate(formatedDate);
    }

}
