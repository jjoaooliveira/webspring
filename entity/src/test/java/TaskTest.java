import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.webapp.entity.Content;
import com.webapp.entity.Task;
import com.webapp.entity.comparator.TaskComparator;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class TaskTest {
    final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
    DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    @DisplayName("Should create a completed task")
    void shouldCreateCompletedTask() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now(zoneId).plusMonths(1);
        Task task = new Task(content, expirationDate);
        task.complete();

        assertEquals(content, task.getContent());
        assertEquals(ZonedDateTime.of(expirationDate, zoneId), task.getExpirationDate());
        assertEquals(true, task.isCompleted());
    }

    @Test
    @DisplayName("Should create a incompleted task")
    void shouldCreateIncompletedTask() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now(zoneId).plusMonths(1);
        Task task = new Task(content, expirationDate);

        assertEquals(content, task.getContent());
        assertEquals(ZonedDateTime.of(expirationDate, zoneId), task.getExpirationDate());
        assertEquals(false, task.isCompleted());
    }

    @Test
    @DisplayName("Should create a incompleted task with expired property equals to true")
    void shouldCreateAIncompletedAndExpiredTask() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("content");
        LocalDateTime creationLocalDateTime = LocalDateTime.parse("2024-12-01T14:30:00");
        LocalDateTime expirationLocalDateTime = LocalDateTime.parse("2024-12-25T23:59:59");
        String creationZonedDateTime = ZonedDateTime.of(creationLocalDateTime, zoneId).toString();
        String expirationZonedDateTime = ZonedDateTime.of(expirationLocalDateTime, zoneId).toString();
        boolean completed = false;
        Task task = new Task(content, creationZonedDateTime, expirationZonedDateTime, completed);

        assertEquals(task.getExpirationDate(), ZonedDateTime.parse(expirationZonedDateTime));
        assertEquals(false, task.isCompleted());
        assertEquals(true, task.isExpired());
    }

    @Test
    @DisplayName("Should create a completed task with expired property equals to true")
    void shouldCreateACompletedAndExpiredTask() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("content");
        LocalDateTime creationLocalDateTime = LocalDateTime.parse("2024-12-01T14:30:00");
        LocalDateTime expirationLocalDateTime = LocalDateTime.parse("2024-12-25T23:59:59");
        ZonedDateTime creationZonedDateTime = ZonedDateTime.of(creationLocalDateTime, zoneId);
        ZonedDateTime expirationZonedDateTime = ZonedDateTime.of(expirationLocalDateTime, zoneId);
        boolean completed = true;
        Task task = new Task(content, creationZonedDateTime.toString(), expirationZonedDateTime.toString(), completed);

        assertEquals(expirationZonedDateTime, task.getExpirationDate());
        assertEquals(true, task.isCompleted());
        assertEquals(true, task.isExpired());
    }

    @Test
    void shouldCreateATaskWithTwoDaysLeft() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now(zoneId).plusDays(2);
        Task task = new Task(content, expirationDate);

        assertEquals("2 dia(s)", task.getTimeLeft().toString());
    }

    @Test
    void shouldCreateATaskWithExpiredTimeLeft() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("content");
        LocalDateTime creationLocalDateTime = LocalDateTime.parse("2024-12-01T14:30:00");
        LocalDateTime expirationLocalDateTime = LocalDateTime.parse("2024-12-25T23:59:59");
        ZonedDateTime creationDate = ZonedDateTime.of(creationLocalDateTime, zoneId);
        ZonedDateTime expirationDate = ZonedDateTime.of(expirationLocalDateTime, zoneId);

        Task task = new Task(content, creationDate.toString(), expirationDate.toString(), false);

        assertEquals("expirado", task.getTimeLeft());
    }

    @Test
    void shouldCreateATaskWithThreeHoursLeft() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now(zoneId).plusHours(3);
        Task task = new Task(content, expirationDate);

        assertEquals("3 hora(s)", task.getTimeLeft());
    }

    @Test
    void shouldCreateATaskWithFifteenMinutesLeft() throws TextLengthOverLimitException, EmptyTextException {
        Content content = Content.create("content");
        LocalDateTime expirationDate = LocalDateTime.now(zoneId).plusMinutes(15);
        Task task = new Task(content, expirationDate);

        assertEquals("15 minuto(s)", task.getTimeLeft());
    }

    @Test
    void shouldReturnSortedTaskListByDaysLeft() throws TextLengthOverLimitException, EmptyTextException {
        LocalDateTime localDateTimeTask = LocalDateTime.now().plusDays(10);
        String stringCreationZonedDateTimeTask = ZonedDateTime.now().toString();
        String stringExpirationZonedDateTimeTask2 = ZonedDateTime.now().plusDays(5).toString();
        String stringExpirationZonedStringTask3 = ZonedDateTime.now().plusDays(15).toString();
        
        List<Task> tasks = List.of(
            new Task(Content.create("content1"), localDateTimeTask),
            new Task(Content.create("content3"), stringCreationZonedDateTimeTask, stringExpirationZonedStringTask3, false),
            new Task(Content.create("content2"), stringCreationZonedDateTimeTask, stringExpirationZonedDateTimeTask2, false)
        );
        ArrayList<Task> tasksArrayList = new ArrayList<>(tasks);
        TaskComparator taskComparator = new TaskComparator();
        Collections.sort(tasksArrayList, taskComparator);

        assertEquals("5 dia(s)", tasksArrayList.get(0).getTimeLeft());
        assertEquals("15 dia(s)", tasksArrayList.get(tasks.size()-1).getTimeLeft());
    }

}
