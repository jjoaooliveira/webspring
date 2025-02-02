package com.webapp.usecase.Mapper;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationMapperTest {

    AnnotationMapper annotationMapper;

    @BeforeEach
    void setUp() {
        annotationMapper = new AnnotationMapper();
    }

    @Test
    void givenAnnotation_whenToOutputDTO_thenReturnNotNullOutputAnnotationDTO() {
        Annotation annotation = new Annotation(
                UUID.randomUUID(),
                new Title("Title 1"),
                new Content("Content 1"),
                new TimeMark()
        );

        OutputAnnotationDTO actualOutputAnnotationDTO = annotationMapper.toOutputDTO(annotation);

        assertNotNull(actualOutputAnnotationDTO);
        assertEquals(annotation.getId(), actualOutputAnnotationDTO.id());
        assertEquals(annotation.getTitle(), actualOutputAnnotationDTO.title());
        assertEquals(annotation.getContent(), actualOutputAnnotationDTO.content());
        assertEquals(annotation.getCreation(), actualOutputAnnotationDTO.creation());

    }

    @Test
    void givenInputAnnotationDTOWithEmptyId_whenToAnnotation_thenReturnNotNullAnnotation() {
        InputAnnotationDTO inputAnnotationDTO1 = new InputAnnotationDTO(
                null,
                "Title 1",
                "Content 1"
        );

        var actualAnnotation = annotationMapper.toAnnotation(inputAnnotationDTO1);

        assertNotNull(actualAnnotation);
        assertEquals(inputAnnotationDTO1.title(), actualAnnotation.getTitle());
        assertEquals(inputAnnotationDTO1.content(), actualAnnotation.getContent());
    }

    @Test
    void givenInputAnnotationDTOWithId_whenToAnnotation_thenReturnNotNullAnnotation() {
        InputAnnotationDTO inputAnnotationDTO1 = new InputAnnotationDTO(
                UUID.randomUUID(),
                "Title 1",
                "Content 1"
        );

        var actualAnnotation = annotationMapper.toAnnotation(inputAnnotationDTO1);

        assertNotNull(actualAnnotation);
        assertEquals(inputAnnotationDTO1.id(), actualAnnotation.getId());
        assertEquals(inputAnnotationDTO1.title(), actualAnnotation.getTitle());
        assertEquals(inputAnnotationDTO1.content(), actualAnnotation.getContent());
    }
}
