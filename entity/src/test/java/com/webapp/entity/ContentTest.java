package com.webapp.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContentTest {

    @Test
    @DisplayName("Create Content With String Input Should Return Valid Content")
    void givenNonNullInputString_whenNewContent_thenShouldReturnContent() {
        //arrange
        String expectedContentText = "Content";

        //act
        var actual = new Content("Content");

        //assert
        assertEquals(expectedContentText, actual.getText(), "The content text is incorrect");
    }
}
