package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveAnnotationUseCase extends UseCase<InputAnnotationDTO, OutputAnnotationDTO> {

    private AnnotationDataAccess repository;
    private AnnotationMapper mapper;

    @Autowired
    public SaveAnnotationUseCase(AnnotationDataAccess annotationDataAccess, AnnotationMapper annotationMapper) {
        this.repository = annotationDataAccess;
        this.mapper = annotationMapper;
    }

    @Override
    public OutputAnnotationDTO execute(InputAnnotationDTO inputAnnotationDTO) {
        Annotation inputAnnotation = mapper.toAnnotation(inputAnnotationDTO);
        Annotation savedAnnotation = repository.save(inputAnnotation);

        return mapper.toOutputDTO(savedAnnotation);
    }
}
