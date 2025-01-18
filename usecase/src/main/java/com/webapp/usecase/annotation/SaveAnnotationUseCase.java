package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.data_access.AnnotationRepository;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveAnnotationUseCase extends UseCase<InputAnnotationDTO, OutputAnnotationDTO> {

    private AnnotationRepository repository;
    private AnnotationMapper mapper;

    @Autowired
    public SaveAnnotationUseCase(AnnotationRepository annotationRepository, AnnotationMapper annotationMapper) {
        this.repository = annotationRepository;
        this.mapper = annotationMapper;
    }

    @Override
    public OutputAnnotationDTO execute(InputAnnotationDTO inputAnnotationDTO) {
        try {
            Annotation inputAnnotation = mapper.toAnnotation(inputAnnotationDTO);
            Annotation savedAnnotation = repository.save(inputAnnotation);

            return mapper.toOutputDTO(savedAnnotation);
        } catch (TextLengthOverLimitException | EmptyTextException e) {
            throw new FailToCreateAnnotationException("It was not possible to create annotation: " + e.getMessage());
        }
    }
}
