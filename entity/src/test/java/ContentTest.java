import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.webapp.entity.Content;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class ContentTest {
    @Test
    public void givenStringOverToLengthLimit_whenNewContent_thenThrowTextLengthOverLimitException() {
        String texto = "A cidade de Cuiabá, capital do estado de Mato Grosso, é conhecida por seu calor intenso e sua " +
            "rica cultura. Fundada em 1719 durante a corrida do ouro, a cidade tem uma história vibrante que se reflete " +
            "em sua arquitetura colonial e em suas tradições. O centro histórico de Cuiabá é um testemunho vivo dessa " +
            "herança, com suas ruas de paralelepípedos e casarões antigos. Além disso, a cidade é um ponto de partida " +
            "ideal para explorar o Pantanal, uma das maiores áreas úmidas do mundo, e a Chapada dos Guimarães, famosa " +
            "por suas formações rochosas e cachoeiras deslumbrantes. A culinária cuiabana é outro destaque, com pratos " +
            "típicos como a mojica de pintado e o pacu assado. A cidade também é conhecida por suas festas tradicionais, " +
            "como o Festival de Cururu e Siriri, que celebra a música e a dança locais. Em suma, Cuiabá é um destino que " +
            "combina história, natureza e cultura de forma única.";

        Assertions.assertThrows(TextLengthOverLimitException.class, () -> {
            new Content(texto);
        });
    }

    @Test
    public void givenEmptyString_whenNewContent_thenThrowEmptyTextException() {
        Assertions.assertThrows(EmptyTextException.class, () -> {
            new Content("");
        });
    }

}
