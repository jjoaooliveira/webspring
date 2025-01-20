import com.webapp.entity.Title;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitlteTest {
    @Test
    void shouldCreateTitleWithDefaultText() {
        Title title = new Title("");

        assertEquals("Insira um titulo...", title.getText());
    }

    @Test
    void shouldCreateTitleWithGivenText() {
        String titleText = "Teste";
        Title title = new Title(titleText);

        assertEquals(titleText, title.getText());
    }
}
