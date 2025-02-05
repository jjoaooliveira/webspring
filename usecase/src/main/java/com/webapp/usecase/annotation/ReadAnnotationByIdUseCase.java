package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ReadAnnotationByIdUseCase extends UseCase<UUID, OutputAnnotationDTO> {
    private AnnotationDataAccess annotationDataAccess;
    private AnnotationMapper annotationMapper;

    @Autowired
    public ReadAnnotationByIdUseCase(AnnotationDataAccess annotationDataAccess, AnnotationMapper annotationMapper) {
        this.annotationDataAccess = annotationDataAccess;
        this.annotationMapper = annotationMapper;
    }

    @Override
    public OutputAnnotationDTO execute(UUID uuid) {
        Annotation returnedAnnotation = annotationDataAccess.findById(uuid);
        return annotationMapper.toOutputDTO(returnedAnnotation);
    }
}
