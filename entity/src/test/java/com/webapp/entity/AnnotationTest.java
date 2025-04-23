package com.webapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnnotationTest {

    @Test
    @DisplayName("Should Create Annotation With Correct Fields")
    void givenValidInputs_whenNewAnnotation_theShouldCreateAnnotation() {
        //arrange
        Title title = new Title("Title");
        Content content = new Content("Content");

        //act
        var actual = new Annotation(title, content);

        //assert
        assertNotNull(actual, "The annotation should not be null");
        assertEquals(title.getText(), actual.getTitle(), "The actual title is not correct");
        assertEquals(content.getText(), actual.getContent(), "The actual content is not correct");
    }
}
