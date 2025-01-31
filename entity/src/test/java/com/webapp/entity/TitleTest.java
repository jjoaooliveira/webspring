package com.webapp.entity;

import com.webapp.entity.api.TitleAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitleTest {

    @Test
    @DisplayName("When Create Empty Title Should Return Default Title")
    void givenEmptyTitleText_whenNew_thenShouldCreateTitleWithDefaultText() {
        //arrange
        String expectedTitle = "Insira um titulo...";

        //act
        var actual = new TitleAPI("");

        //assert
        assertEquals(expectedTitle, actual.getText());
    }

    @Test
    @DisplayName("When Create Title With Success Should Return Title")
    void givenTitleText_whenNew_shouldCreateTitleWithGivenText() {
        //arrange
        String titleText = "Teste";

        //act
        var actual = new TitleAPI(titleText);

        //assert
        assertEquals(titleText, actual.getText());
    }
}
