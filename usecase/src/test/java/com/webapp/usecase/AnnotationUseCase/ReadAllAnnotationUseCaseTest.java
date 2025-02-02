package com.webapp.usecase.AnnotationUseCase;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.TimeMark;
import com.webapp.entity.Title;
import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReadAllAnnotationUseCaseTest {
    @Mock
    AnnotationDataAccess mockAnnotationDataAccess;

    AnnotationMapper annotationMapper;

    Annotation annotation1, annotation2;

    UseCaseAPI useCaseAPI;

    @BeforeEach
    void setUp() {
        useCaseAPI = new UseCaseAPI();
        annotation1 = new Annotation(
                UUID.randomUUID(),
                new Title(""),
                new Content("Content 1"),
                new TimeMark()
        );
        annotation2 = new Annotation(
                UUID.randomUUID(),
                new Title(""),
                new Content("Content 2"),
                new TimeMark()
        );
        annotationMapper = new AnnotationMapper();
    }

    @Test
    void givenReadAllAnnotation_whenExecute_thenReturnListOfAnnotationWithSizeEquals2() {
        //arrange
        int expectSize = 2;
        String expectedContent1 = "Content 1";
        String expectedContent2 = "Content 2";
        List<Annotation> annotationList = List.of(annotation1, annotation2);

        when(mockAnnotationDataAccess.findAll()).thenReturn(annotationList);

        //act
        var actualAnnotationOutputDTOS = useCaseAPI.readAllAnnotation(annotationMapper, mockAnnotationDataAccess);

        //assert
        assertNotNull(actualAnnotationOutputDTOS);
        assertEquals(expectSize, actualAnnotationOutputDTOS.size());
        assertEquals(expectedContent1, actualAnnotationOutputDTOS.get(0).content());
        assertEquals(expectedContent2, actualAnnotationOutputDTOS.get(1).content());
    }
}
