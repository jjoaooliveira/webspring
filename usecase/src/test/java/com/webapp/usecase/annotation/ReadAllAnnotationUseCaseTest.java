package com.webapp.usecase.annotation;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;
import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReadAllAnnotationUseCaseTest {
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
    @DisplayName("When Execute With Success Should Return Annotation List")
    void givenAnnotationList_whenExecute_thenShouldCallFindAllMethodOneTimeAndReturnDTOList() {
        //arrange
        int expectSize = 2;
        Annotation annotation1 = new Annotation(
                UUID.randomUUID(),
                new Title(""),
                new Content("Content 1"),
                new TimeMark(OffsetDateTime.now())
        );

        Annotation annotation2 = new Annotation(
                UUID.randomUUID(),
                new Title(""),
                new Content("Content 2"),
                new TimeMark(OffsetDateTime.now())
        );
        List<Annotation> annotationList = List.of(annotation1, annotation2);

        when(mockAnnotationDataAccess.findAll()).thenReturn(annotationList);

        //act
        var actual = useCaseAPI.readAllAnnotation(annotationMapper, mockAnnotationDataAccess);

        //assert
        verify(mockAnnotationDataAccess, times(1)
                .description("Should call findAll method only one time"))
                .findAll();
        assertNotNull(actual, "The actual list should not be null");
        assertEquals(expectSize, actual.size(), "The actual list size should be 2");
    }
}
