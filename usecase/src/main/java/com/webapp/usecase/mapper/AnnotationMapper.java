package com.webapp.usecase.mapper;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.Title;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dto.annotation.AnnotationDTO;
import com.webapp.usecase.dto.annotation.NewAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import org.springframework.stereotype.Component;

@Component
public class AnnotationMapper {
    public OutputAnnotationDTO toOutputDTO(Annotation annotation) {
        String creationDate = annotation.getCreationDate().toLocalDate().toString();
        String creationTime = annotation.getCreationDate().toLocalTime().toString();

        return new OutputAnnotationDTO(
                annotation.getId(),
                annotation.getTitle(),
                annotation.getContent(),
                creationDate,
                creationTime
        );
    }

    public Annotation toAnnotation(AnnotationDTO annotationDTO) throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title(annotationDTO.title());
        Content content = Content.create(annotationDTO.content());

        return new Annotation(
                annotationDTO.id(),
                title,
                content,
                annotationDTO.creationDate()
        );
    }

    public AnnotationDTO toAnnotationDTO(Annotation annotation)  {
        return new AnnotationDTO(
                annotation.getId(),
                annotation.getTitle(),
                annotation.getContent(),
                annotation.getCreationDate().toString()
        );
    }

    public Annotation toAnnotation(NewAnnotationDTO newAnnotation) throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title(newAnnotation.title());
        Content content = Content.create(newAnnotation.content());

        return new Annotation(
                title,
                content
        );
    }
}
