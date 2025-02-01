package com.webapp.usecase.AnnotationUseCase;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.exception.FailToCreateAnnotationException;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaveAnnotationUseCaseTest {

    @Mock
    AnnotationDataAccess mockAnnotationDataAccess;

    AnnotationMapper annotationMapper;

    InputAnnotationDTO inputAnnotationDTO;
    Annotation returnedDatabaseAnnotation;

    UseCaseAPI useCaseAPI;

    @BeforeEach
    void setUp() {
        useCaseAPI = new UseCaseAPI();
        annotationMapper = new AnnotationMapper();
        UUID uuid = UUID.randomUUID();
        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        inputAnnotationDTO = new InputAnnotationDTO(
                Optional.of(uuid),
                "Title 1",
                "Content 1"
        );

        returnedDatabaseAnnotation = new Annotation(
                uuid,
                new Title("Title 1"),
                new Content("Content 1"),
                new TimeMark(offsetDateTime));
    }

    @Test
    void givenAnnotationUseCase_whenExecute_thenReturnNotNullOutputAnnotationDTO() {

        when(mockAnnotationDataAccess.save(any())).thenReturn(returnedDatabaseAnnotation);

        var actual = useCaseAPI.saveAnnotation(inputAnnotationDTO, annotationMapper, mockAnnotationDataAccess);
        assertNotNull(actual);
        assertEquals(returnedDatabaseAnnotation.getId(), actual.id());
        assertEquals(returnedDatabaseAnnotation.getTitle(), actual.title());
        assertEquals(returnedDatabaseAnnotation.getContent(), actual.content());
        assertEquals(returnedDatabaseAnnotation.getCreation(), actual.creation());
    }
}
