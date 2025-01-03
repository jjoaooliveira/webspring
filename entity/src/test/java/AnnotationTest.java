import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import com.webapp.entity.Title;
import org.junit.jupiter.api.Test;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class AnnotationTest {

    @Test
    void shouldCreateAnnotation() throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title("");
        Content content = Content.create("Hello, world!");
        Annotation annotation = new Annotation(title, content);

        assertEquals(title.getText(), annotation.getTitle());
        assertEquals(content.getText(), annotation.getContent());
    }

    @Test
    void shouldCreateAnnotationWithIdAndCreationDateArgument() throws TextLengthOverLimitException, EmptyTextException {
        Long id = 1L;
        Title title = new Title("Teste");
        Content content = Content.create("Hello, world!");
        LocalDateTime creationLocalDateTime = LocalDateTime.parse("2024-12-25T23:59:59");
        String stringDate = creationLocalDateTime.toString();
        Annotation annotation = new Annotation(id, title, content, stringDate);

        assertEquals(id, annotation.getId());
        assertEquals(title.getText(), annotation.getTitle());
        assertEquals(content.getText(), annotation.getContent());
        assertEquals(creationLocalDateTime, annotation.getCreationDate());
        assertEquals(stringDate, annotation.getCreationDate().toString());
    }

    @Test
    void shouldSetNewContentAfterCreation() throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title("");
        Content content = Content.create("Hello, world!");
        Annotation annotation = new Annotation(title, content);
        Content newContent = Content.create("Merry Christmas, world!");
        annotation.setContent(newContent);

        assertEquals(title.getText(), annotation.getTitle());
        assertEquals(newContent.getText(), annotation.getContent());
    }
}
