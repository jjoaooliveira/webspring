import com.webapp.entity.Title;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitleTest {
    @Test
    void givenEmptyTitleText_whenNew_thenShouldCreateTitleWithDefaultText() {
        Title title = new Title("");

        assertEquals("Insira um titulo...", title.getText());
    }

    @Test
    void givenTitleText_whenNew_shouldCreateTitleWithGivenText() {
        String titleText = "Teste";
        Title title = new Title(titleText);

        assertEquals(titleText, title.getText());
    }
}
