package com.webapp.usecase.annotation;

import com.webapp.usecase.SimpleInputUseCase;
import com.webapp.usecase.data_access.AnnotationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class DeleteAnnotationUseCase extends SimpleInputUseCase<Long> {
    private AnnotationRepository repository;

    @Autowired
    public DeleteAnnotationUseCase(AnnotationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) {
        Objects.requireNonNull(id);
        repository.delete(id);
    }
}
