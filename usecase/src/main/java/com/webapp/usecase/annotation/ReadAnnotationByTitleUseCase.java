package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.usecase.ReadByUseCase;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadAnnotationByTitleUseCase extends ReadByUseCase<String, List<OutputAnnotationDTO>> {
    private final AnnotationDataAccess annotationDataAccess;
    private final AnnotationMapper annotationMapper;

    @Autowired
    public ReadAnnotationByTitleUseCase(AnnotationDataAccess annotationDataAccess, AnnotationMapper annotationMapper) {
        this.annotationDataAccess = annotationDataAccess;
        this.annotationMapper = annotationMapper;
    }

    @Override
    public List<OutputAnnotationDTO> execute(String title) {
        List<Annotation> annotationList = annotationDataAccess.findByTitle(title);
        return annotationList.stream().map(annotationMapper::toOutputDTO).toList();
    }
}
