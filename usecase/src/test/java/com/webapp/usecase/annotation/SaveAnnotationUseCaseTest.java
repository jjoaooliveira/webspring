package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;
import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveAnnotationUseCaseTest {

    @Mock
    AnnotationDataAccess mockAnnotationDataAccess;

    AnnotationMapper annotationMapper;

    UseCaseAPI useCaseAPI;

    @BeforeEach
    void setUp() {
        useCaseAPI = new UseCaseAPI();
        annotationMapper = new AnnotationMapper();
    }

    @Test
    @DisplayName("When Execute With Success Should Return Annotation Saved")
    void givenInputAnnotationDTO_whenExecute_thenShouldCallSaveMethodOneTimeAndReturnOutputAnnotationDTO() {
        //arrange
        UUID uuid = UUID.randomUUID();

        InputAnnotationDTO inputAnnotationDTO = new InputAnnotationDTO(
                uuid,
                "Title 1",
                "Content 1",
                OffsetDateTime.parse("2025-01-01T00:00:00-03:00")
        );

        Annotation returnedDatabaseAnnotation = new Annotation(
                uuid,
                new Title("Title 1"),
                new Content("Content 1"),
                new TimeMark(OffsetDateTime.parse("2025-01-01T00:00:00-03:00"))
        );

        when(mockAnnotationDataAccess.save(any(Annotation.class))).thenReturn(returnedDatabaseAnnotation);

        //act
        var actual = useCaseAPI.saveAnnotation(inputAnnotationDTO, annotationMapper, mockAnnotationDataAccess);

        //assert
        verify(mockAnnotationDataAccess, times(1)
                .description("Should call save method only one time"))
                .save(any(Annotation.class));
        assertNotNull(actual, "The actual annotation should not be null");
    }
}
