import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.webapp.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class TaskTest {
    @Mock
    Title mockTitle;

    @Mock
    Content mockContent;

    @Mock
    TimedMark mockTimedMark;

    @Mock
    TimeMark mockTimeMark;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a expired task")
    void givenExpiredTask_whenIsExpired_thenReturnTrue() {
        boolean expectExpired = true;

        when(mockTimedMark.isExpired()).thenReturn(true);

        Task actualTask = new Task(mockTitle, mockContent, mockTimeMark, mockTimedMark);
        var actualExpired = actualTask.isExpired();

        assertEquals(expectExpired, actualExpired);
    }

    @Test
    @DisplayName("Should create a completed task")
    void givenCompletedTask_whenIsCompleted_thenReturnTrue() {
        boolean expectCompleted = true;

        Task actualTask = new Task(mockTitle, mockContent, mockTimeMark, mockTimedMark);
        actualTask.complete();

        var actualCompleted = actualTask.isCompleted();

        assertEquals(expectCompleted, actualCompleted);
    }

    @Test
    @DisplayName("Should create a completed task with boolean and id as argument")
    void givenIdAndCompletedArg_whenCreatingNewTask_thenReturnTask() {
        UUID uuid = UUID.randomUUID();
        Task actualTask = new Task(uuid, mockTitle, mockContent, mockTimeMark, mockTimedMark, true);

        assertNotNull(actualTask);
    }

    @Test
    @DisplayName("Should return task title and time left as string")
    void givenTask_whenToString_thenReturnStringWithTaskContentAndTimeLeft() {
        String expectString = "Teste - 3h";

        when(mockTitle.getText()).thenReturn("Teste");
        when(mockTimedMark.getTimeLeft()).thenReturn("3h");

        Task task = new Task(mockTitle, mockContent, mockTimeMark, mockTimedMark);
        var actualString = task.toString();

        assertEquals(expectString, actualString);
    }
}
