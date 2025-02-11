package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.SimpleReturnUseCase;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadAllAnnotationUseCase extends SimpleReturnUseCase<List<OutputAnnotationDTO>> {
    private AnnotationDataAccess annotationDataAccess;
    private AnnotationMapper annotationMapper;

    @Autowired
    public ReadAllAnnotationUseCase(AnnotationDataAccess annotationDataAccess, AnnotationMapper annotationMapper) {
        this.annotationDataAccess = annotationDataAccess;
        this.annotationMapper = annotationMapper;
    }

    @Override
    public List<OutputAnnotationDTO> execute() {
        List<Annotation> annotations = annotationDataAccess.findAll();

        return annotations.stream()
            .map(annotationMapper::toOutputDTO)
            .toList();
    }
}
