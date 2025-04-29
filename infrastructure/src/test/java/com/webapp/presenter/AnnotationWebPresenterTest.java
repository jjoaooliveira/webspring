package com.webapp.presenter;

import static org.junit.jupiter.api.Assertions.*;

import com.webapp.usecase.annotation.AnnotationOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.time.Instant;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.Random.class)
@DisplayName("Annotation Web Presenter Tests")
public class AnnotationWebPresenterTestTest {

    AnnotationWebPresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new AnnotationWebPresenter();
    }

    @Test
    @DisplayName("Presenter toEntity Method Should Return Correct EntityModel Object")
    void givenAnnotationOutputData_whenToEntity_thenShouldReturnCorrectEntityModel() {
        //arrange
        AnnotationOutputData annotationOutputData = new AnnotationOutputData(
                UUID.randomUUID(),
                "title",
                "content",
                Instant.now().toString()
        );

        //act
        var actual = presenter.toEntityModel(annotationOutputData);

        //assert
        assertEquals(annotationOutputData, actual.getContent());
    }

    @Test
    @DisplayName("Presenter toEntity Method Should Return Correct EntityModel Object With Valid Content")
    void givenAnnotationOutputData_whenToEntity_thenShouldReturnCorrectEntityModelWithValidContent() {
        //arrange
        AnnotationOutputData annotationOutputData = new AnnotationOutputData(
                UUID.randomUUID(),
                "title",
                "content",
                Instant.now().toString()
        );

        //act
        var actual = presenter.toEntityModel(annotationOutputData);

        //assert
        assertNotNull(actual.getContent());
        assertEquals(annotationOutputData.getId(), actual.getContent().getId());
        assertEquals(annotationOutputData.getTitle(), actual.getContent().getTitle());
        assertEquals(annotationOutputData.getContent(), actual.getContent().getContent());
        assertEquals(annotationOutputData.getCreationDate(), actual.getContent().getCreationDate());
    }
}