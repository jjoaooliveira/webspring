package com.webapp.entity;

import com.webapp.entity.api.ContentAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

import static org.junit.jupiter.api.Assertions.*;

public class ContentTest {

    @Test
    @DisplayName("When Create a Success Content Should Return Valid Content")
    void givenContent_whenNewContent_thenShouldReturnContent() {
        //arrange
        String expectedContent = "Content";

        //act
        var actual = new ContentAPI("Content");

        //assert
        assertNotNull(actual, "The content should not be null");
        assertEquals(expectedContent, actual.getText(), "The content text is incorrect");
    }

    @Test
    @DisplayName("When Create Over Limit Content Should Throw Exception")
    public void givenStringOverToLengthLimit_whenNewContent_thenThrowTextLengthOverLimitException() {
        //arrange

        String text = "A cidade de Cuiabá, capital do estado de Mato Grosso, é conhecida por seu calor intenso e sua " +
            "rica cultura. Fundada em 1719 durante a corrida do ouro, a cidade tem uma história vibrante que se reflete " +
            "em sua arquitetura colonial e em suas tradições. O centro histórico de Cuiabá é um testemunho vivo dessa " +
            "herança, com suas ruas de paralelepípedos e casarões antigos. Além disso, a cidade é um ponto de partida " +
            "ideal para explorar o Pantanal, uma das maiores áreas úmidas do mundo, e a Chapada dos Guimarães, famosa " +
            "por suas formações rochosas e cachoeiras deslumbrantes. A culinária cuiabana é outro destaque, com pratos " +
            "típicos como a mojica de pintado e o pacu assado. A cidade também é conhecida por suas festas tradicionais, " +
            "como o Festival de Cururu e Siriri, que celebra a música e a dança locais. Em suma, Cuiabá é um destino que " +
            "combina história, natureza e cultura de forma única.";

        //act/assert
        TextLengthOverLimitException exception = assertThrows(TextLengthOverLimitException.class, () -> new ContentAPI(text));

        //assert
        assertEquals(
                "The content length must have up to 100 characters",
                exception.getMessage(),
                "The exception message is incorrect"
        );
    }

    @Test
    @DisplayName("When Create Empty Content Should Throw Exception")
    public void givenEmptyString_whenNewContent_thenThrowEmptyTextException() {
        //arrange

        //act/assert
        EmptyTextException exception = assertThrows(EmptyTextException.class, () -> new ContentAPI(""));

        //assert
        assertEquals(
                "The content cannot be empty",
                exception.getMessage(),
                "The content cannot be empty"
        );
    }

}
