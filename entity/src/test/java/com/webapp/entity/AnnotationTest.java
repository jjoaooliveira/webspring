package com.webapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.webapp.entity.api.AnnotationAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

public class AnnotationTest {
    String title;
    String content;
    OffsetDateTime offsetDateTime;

    @BeforeEach
    void setUp() {
        title = "Title 1";
        content = "Content 1";
        offsetDateTime = OffsetDateTime.now();
    }

    @Test
    @DisplayName("When Creating Annotation With Success Should Contains Valid Fields")
    void givenAnnotation_whenNewAnnotation_theShouldCreateAnnotation() {
        //arrange

        //act
        var actual = new AnnotationAPI(title, content, offsetDateTime);

        //assert
        assertNotNull(actual, "The annotation should not be null");
        assertEquals(title, actual.getTitle());
        assertEquals(content, actual.getContent());
        assertEquals(offsetDateTime, actual.getCreation());
    }

    @Test
    @DisplayName("When Call To String Method Should Return Valid String")
    void givenAnnotation_whenToString_thenReturnAnnotationString() {
        //arrange
        String expectedString = "Title 1";

        //act
        var actual = new AnnotationAPI(title, content, offsetDateTime);

        //assert
        assertEquals(expectedString, actual.annotationToString(), "The string is incorrect");
    }
}
