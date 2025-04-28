package com.webapp.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitleTest {

    @Test
    @DisplayName("Create Title With Not Empty Input Should Return Title With Valid Field")
    void givenTitleText_whenNew_shouldCreateTitleWithGivenText() {
        //arrange
        String titleText = "Test";

        //act
        var actual = new Title(titleText);

        //assert
        assertEquals(titleText, actual.getText(), "Title text is not correct");
    }
}
