package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.dataaccess.AnnotationRepository;
import com.webapp.usecase.dto.annotation.AnnotationDTO;
import com.webapp.usecase.dto.annotation.NewAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveAnnotationUseCase extends UseCase<NewAnnotationDTO, OutputAnnotationDTO> {

    private AnnotationRepository repository;
    private AnnotationMapper mapper;

    @Autowired
    public SaveAnnotationUseCase(AnnotationRepository annotationRepository, AnnotationMapper annotationMapper) {
        this.repository = annotationRepository;
        this.mapper = annotationMapper;
    }

    @Override
    public OutputAnnotationDTO execute(NewAnnotationDTO newAnnotationDTO) {
        try {
            Annotation annotationFromInput = mapper.toAnnotation(newAnnotationDTO);
            AnnotationDTO annotationDTO = mapper.toAnnotationDTO(annotationFromInput);
            AnnotationDTO returnedDatabaseAnnotationDTO = repository.save(annotationDTO);
            Annotation returnedAnnotation = mapper.toAnnotation(returnedDatabaseAnnotationDTO);
            return mapper.toOutputDTO(returnedAnnotation);
        } catch (TextLengthOverLimitException | EmptyTextException e) {
            throw new RuntimeException(); //TODO implementar exception
        }
    }
}
