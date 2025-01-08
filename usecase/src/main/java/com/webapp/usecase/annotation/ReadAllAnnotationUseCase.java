package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.SimpleReturnUseCase;
import com.webapp.usecase.dataaccess.AnnotationRepository;
import com.webapp.usecase.dto.annotation.AnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReadAllAnnotationUseCase extends SimpleReturnUseCase<List<OutputAnnotationDTO>> {
    private AnnotationRepository taskRepository;
    private AnnotationMapper taskMapper;

    @Autowired
    public ReadAllAnnotationUseCase(AnnotationRepository taskRepository, AnnotationMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<OutputAnnotationDTO> execute() {
        List<AnnotationDTO> annotationDTOSList = taskRepository.findAll();
        List<Annotation> annotations = annotationDTOSList.stream()
                .map(annotation -> {
                    try {
                        return taskMapper.toAnnotation(annotation);
                    } catch (EmptyTextException | TextLengthOverLimitException e) {
                        throw new RuntimeException(); //TODO implementar exception
                    }
                })
                .toList();

        return annotations.stream()
                .map(taskMapper::toOutputDTO)
                .toList();
    }
}
