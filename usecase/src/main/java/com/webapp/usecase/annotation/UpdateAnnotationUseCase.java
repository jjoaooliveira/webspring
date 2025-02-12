package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateAnnotationUseCase extends UseCase<InputAnnotationDTO, OutputAnnotationDTO> {
    private AnnotationDataAccess dataAccess;
    private AnnotationMapper mapper;

    @Autowired
    public UpdateAnnotationUseCase(AnnotationDataAccess dataAccess, AnnotationMapper mapper) {
        this.dataAccess = dataAccess;
        this.mapper = mapper;
    }

    @Override
    public OutputAnnotationDTO execute(InputAnnotationDTO inputAnnotationDTO) {
        Annotation annotation = dataAccess.findById(inputAnnotationDTO.id());
        annotation.setTitle(inputAnnotationDTO.title());
        annotation.setContent(inputAnnotationDTO.content());

        Annotation persistedUpdatedAnnotation = dataAccess.save(annotation);
        return mapper.toOutputDTO(persistedUpdatedAnnotation);
    }
}
