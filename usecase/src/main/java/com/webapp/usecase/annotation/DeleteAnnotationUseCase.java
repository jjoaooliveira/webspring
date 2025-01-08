package com.webapp.usecase.annotation;

import com.webapp.usecase.SimpleInputUseCase;
import com.webapp.usecase.dataaccess.AnnotationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteAnnotationUseCase extends SimpleInputUseCase<Long> {
    private AnnotationRepository repository;

    @Autowired
    public DeleteAnnotationUseCase(AnnotationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) {
        repository.delete(id);
    }
}
