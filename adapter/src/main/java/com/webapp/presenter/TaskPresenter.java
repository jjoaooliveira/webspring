package com.webapp.presenter;

import com.webapp.controller.web.TaskController;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TaskPresenter {
    public EntityModel<OutputTaskDTO> toEntityModel(OutputTaskDTO taskDTO) {
        return EntityModel.of(taskDTO,
                linkTo(methodOn(TaskController.class)
                        .getTaskById(taskDTO.id()))
                        .withSelfRel(),
                linkTo(methodOn(TaskController.class)
                        .getAllTask())
                        .withRel("tasks")
        );
    }

    public CollectionModel<EntityModel<OutputTaskDTO>> toCollectionModel(Collection<EntityModel<OutputTaskDTO>> collection) {
        return CollectionModel.of(
                collection, linkTo(methodOn(TaskController.class)
                        .getAllTask())
                        .withSelfRel());
    }
}
