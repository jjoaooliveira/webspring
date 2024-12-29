import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class AnnotationTest {
    final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

    @Test
    void shouldCreateAnnotation() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("Hello, world!");
        Annotation annotation = new Annotation(content);

        assertEquals(content, annotation.getContent());
    }

    @Test
    void shouldCreateAnnotationWithCreationDateAsString() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("Hello, world!");
        LocalDateTime expirationLocalDateTime = LocalDateTime.parse("2024-12-25T23:59:59");
        String stringDate = ZonedDateTime.of(expirationLocalDateTime, zoneId).toString();
        Annotation annotation = new Annotation(content, stringDate);

        assertEquals(content, annotation.getContent());
        assertEquals(stringDate, annotation.getCreationDate().toString());
    }

    @Test
    void shouldSetNewContentAfterCreation() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("Hello, world!");
        Annotation annotation = new Annotation(content);
        Content newContent = Content.create("Merry Christmas, world!");
        annotation.setContent(newContent);

        assertEquals(newContent, annotation.getContent());
    }
}
