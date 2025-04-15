package com.webapp.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

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

    @Test
    @DisplayName("Create Content With String Input Over Character Length Limit Should Throw Exception")
    public void givenStringOverLengthLimit_whenNewContent_thenShouldThrowTextLengthOverLimitException() {
        //arrange
        String stringInputOverLimit = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";

        //act/assert
        assertThrows(TextLengthOverLimitException.class, () -> new Content(stringInputOverLimit));
    }

    @Test
    @DisplayName("Create Content With String Input Over Character Length Limit Should Throw TextLengthOverLimitException With Correct Error Message")
    public void givenStringOverLengthLimit_whenNewContent_thenShouldThrowTextLengthOverLimitExceptionWithCorrectErrorMessage() {
        //arrange
        String stringInputOverLimit = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";

        //act/assert
        TextLengthOverLimitException exception = assertThrows(TextLengthOverLimitException.class, () -> new Content(stringInputOverLimit));

        //assert
        assertEquals(
                "The content length must have up to 100 characters",
                exception.getMessage(),
                "The exception message is incorrect"
        );
    }

    @Test
    @DisplayName("Create Empty Content Should Throw IllegalArgumentException")
    public void givenEmptyString_whenNewContent_thenShouldThrowEmptyTextException() {
        //arrange
        String emptyInputString = "";

        //act/assert
        assertThrows(
                IllegalArgumentException.class,
                () -> new Content(emptyInputString),
                "Empty string input should throw IllegalArgumentException"
        );
    }

    @Test
    @DisplayName("Create Empty Content Should Throw IllegalArgumentException With Correct Error Message")
    void givenEmptyString_whenNewContent_thenShouldThrowIllegalArgumentExceptionWithCorrectErrorMessage() {
        //arrange
        String emptyInputString = "";

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Content(emptyInputString));

        //assert
        assertEquals(
                "The content cannot be empty",
                exception.getMessage(),
                "The exception message is incorrect"
        );
    }
}
