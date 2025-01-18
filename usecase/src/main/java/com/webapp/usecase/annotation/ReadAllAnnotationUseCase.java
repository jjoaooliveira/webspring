package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.SimpleReturnUseCase;
import com.webapp.usecase.data_access.AnnotationRepository;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReadAllAnnotationUseCase extends SimpleReturnUseCase<List<OutputAnnotationDTO>> {
    private AnnotationRepository annotationRepository;
    private AnnotationMapper annotationMapper;

    @Autowired
    public ReadAllAnnotationUseCase(AnnotationRepository annotationRepository, AnnotationMapper annotationMapper) {
        this.annotationRepository = annotationRepository;
        this.annotationMapper = annotationMapper;
    }

    @Override
    public List<OutputAnnotationDTO> execute() {
        List<Annotation> annotations = annotationRepository.findAll();

        return annotations.stream()
            .map(annotationMapper::toOutputDTO)
            .toList();
    }
}
