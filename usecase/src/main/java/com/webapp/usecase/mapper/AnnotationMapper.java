package com.webapp.usecase.mapper;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import org.springframework.stereotype.Component;

@Component
public class AnnotationMapper {
    public OutputAnnotationDTO toOutputDTO(Annotation annotation) {
        return new OutputAnnotationDTO(
                annotation.getId(),
                annotation.getTitle(),
                annotation.getContent(),
                annotation.getCreation()
        );
    }

    public Annotation toAnnotation(InputAnnotationDTO inputAnnotationDTO) {
        return new Annotation(
                inputAnnotationDTO.id(),
                new Title(inputAnnotationDTO.title()),
                new Content(inputAnnotationDTO.content()),
                new TimeMark(inputAnnotationDTO.creation())
            );
    }
}
