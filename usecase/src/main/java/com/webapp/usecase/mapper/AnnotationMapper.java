package com.webapp.usecase.mapper;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
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
                annotation.getCreationDate(),
                annotation.getCreationTime()
        );
    }

    public Annotation toAnnotation(InputAnnotationDTO inputAnnotationDTO) throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title(inputAnnotationDTO.title());
        Content content = new Content(inputAnnotationDTO.content());
        TimeMark timeMark = new TimeMark();

        if(inputAnnotationDTO.id().isPresent()) {
            return new Annotation(
                    inputAnnotationDTO.id().get(),
                    title,
                    content,
                    timeMark
            );
        }
        return new Annotation(
                title,
                content,
                timeMark
        );
    }
}
