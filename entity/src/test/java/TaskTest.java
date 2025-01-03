import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.webapp.entity.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.webapp.entity.Content;
import com.webapp.entity.Task;
import com.webapp.entity.comparator.TaskComparator;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class TaskTest {
    final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

    @Test
    @DisplayName("Should create a completed task")
    void shouldCreateCompletedTask() throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title("");
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now(zoneId).plusMonths(1);
        Task task = new Task(title, content, expirationDate);
        task.complete();

        assertEquals(title.getText(), task.getTitle());
        assertEquals(content.getText(), task.getContent());
        assertEquals(ZonedDateTime.of(expirationDate, zoneId), task.getExpirationDate());
        assertEquals(true, task.isCompleted());
    }

    @Test
    @DisplayName("Should create a uncompleted task")
    void shouldCreateUncompletedTask() throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title("Teste");
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now().plusMonths(1);
        Task task = new Task(title, content, expirationDate);

        assertEquals(title.getText(), task.getTitle());
        assertEquals(content.getText(), task.getContent());
        assertEquals(ZonedDateTime.of(expirationDate, zoneId), task.getExpirationDate());
        assertEquals(false, task.isCompleted());
    }

    @Test
    @DisplayName("Should create a uncompleted task with expired property equals to true")
    void shouldCreateAUncompletedAndExpiredTask() throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title("Teste");
        Content content = Content.create("content");
        LocalDateTime expirationLocalDateTime = LocalDateTime.parse("2024-12-25T23:59:59");
        Task task = new Task(title, content, expirationLocalDateTime);

        assertEquals(title.getText(), task.getTitle());
        assertEquals(content.getText(), task.getContent());
        assertEquals(expirationLocalDateTime, task.getExpirationDate().toLocalDateTime());
        assertEquals(false, task.isCompleted());
        assertEquals(true, task.isExpired());
    }

    @Test
    @DisplayName("Should create a completed task with expired property equals to true")
    void shouldCreateCompletedAndExpiredTaskWithDatabaseArguments() throws TextLengthOverLimitException, EmptyTextException {
        Long id = 1L;
        Title title = new Title("Teste");
        Content content = Content.create("content");

        LocalDateTime creationLocalDateTime = LocalDateTime.parse("2024-12-01T14:30:00");
        LocalDateTime expirationLocalDateTime = LocalDateTime.parse("2024-12-25T23:59:59");
        ZonedDateTime creationZonedDateTime = ZonedDateTime.of(creationLocalDateTime, zoneId);
        ZonedDateTime expirationZonedDateTime = ZonedDateTime.of(expirationLocalDateTime, zoneId);
        String stringCreationDate = creationZonedDateTime.toString();
        String stringExpirationDate = expirationZonedDateTime.toString();

        boolean completed = true;
        Task task = new Task(id, title, content, stringCreationDate, stringExpirationDate, completed);

        assertEquals(id, task.getId());
        assertEquals(title.getText(), task.getTitle());
        assertEquals(content.getText(), task.getContent());
        assertEquals(creationZonedDateTime, task.getCreationDate());
        assertEquals(expirationZonedDateTime, task.getExpirationDate());
        assertEquals(true, task.isCompleted());
        assertEquals(true, task.isExpired());
    }

    @Test
    void shouldCreateATaskWithTwoDaysLeft() throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title("Teste");
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now(zoneId).plusDays(2);
        Task task = new Task(title, content, expirationDate);

        assertEquals("2 dia(s)", task.getTimeLeft());
    }

    @Test
    void shouldCreateATaskWithExpiredTimeLeftWithDatabaseParams() throws TextLengthOverLimitException, EmptyTextException {
        Long id = 1L;
        Title title = new Title("");
        Content content = Content.create("content");
        LocalDateTime creationLocalDateTime = LocalDateTime.parse("2024-12-01T14:30:00");
        LocalDateTime expirationLocalDateTime = LocalDateTime.parse("2024-12-25T23:59:59");
        ZonedDateTime creationDate = ZonedDateTime.of(creationLocalDateTime, zoneId);
        ZonedDateTime expirationDate = ZonedDateTime.of(expirationLocalDateTime, zoneId);
        String stringCreationDate = creationDate.toString();
        String stringExpirationDate = expirationDate.toString();

        Task task = new Task(id, title, content, stringCreationDate, stringExpirationDate, false);

        assertEquals("expirado", task.getTimeLeft());
    }

    @Test
    void shouldCreateATaskWithThreeHoursLeft() throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title("");
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now(zoneId).plusHours(3);
        Task task = new Task(title, content, expirationDate);

        assertEquals("3 hora(s)", task.getTimeLeft());
    }

    @Test
    void shouldCreateATaskWithFifteenMinutesLeft() throws TextLengthOverLimitException, EmptyTextException {
        Title title = new Title("");
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now(zoneId).plusMinutes(15);
        Task task = new Task(title, content, expirationDate);

        assertEquals("15 minuto(s)", task.getTimeLeft());
    }
}
