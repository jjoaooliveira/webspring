package com.webapp.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class AnnotationTest {

    @Test
    @DisplayName("Should Create Annotation With Valid Inputs")
    void givenValidInputs_whenNewAnnotation_theShouldCreateAnnotation() {
        //arrange
        Title title = new Title("Title");
        Content content = new Content("Content");
        Instant instant = Instant.now();

        //act
        var actual = new Annotation(title, content, instant);

        //assert
        assertNotNull(actual, "The annotation should not be null");
    }
}
