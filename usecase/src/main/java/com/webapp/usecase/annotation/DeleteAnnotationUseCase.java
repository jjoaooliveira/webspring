package com.webapp.usecase.annotation;

import com.webapp.usecase.SimpleInputUseCase;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.UUID;

public class DeleteAnnotationUseCase extends SimpleInputUseCase<UUID> {
    private AnnotationDataAccess repository;

    @Autowired
    public DeleteAnnotationUseCase(AnnotationDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id) {
        Objects.requireNonNull(id);
        repository.delete(id);
    }
}
