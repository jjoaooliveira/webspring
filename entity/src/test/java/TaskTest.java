import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.webapp.entity.TimedControl;
import com.webapp.entity.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.webapp.entity.Content;
import com.webapp.entity.Task;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class TaskTest {
    @Mock
    Title mockTitle;

    @Mock
    Content mockContent;

    @Mock
    TimedControl mockTimedControl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a expired task")
    void givenExpiredTask_whenIsExpired_thenReturnTrue() {
        boolean expectExpired = true;

        when(mockTimedControl.isExpired()).thenReturn(true);

        Task actualTask = new Task(mockTitle, mockContent, mockTimedControl);
        var actualExpired = actualTask.isExpired();

        assertEquals(expectExpired, actualExpired);
    }

    @Test
    @DisplayName("Should create a completed task")
    void givenTask_whenIsCompleted_thenReturnTrue() {
        boolean expectCompleted = true;

        Task actualTask = new Task(mockTitle, mockContent, mockTimedControl);
        actualTask.complete();

        var actualCompleted = actualTask.isCompleted();

        assertEquals(expectCompleted, actualCompleted);
    }

    @Test
    @DisplayName("Should create a completed task with boolean as argument")
    void givenTask_whenTaskConstructor_thenReturnTask() {
        boolean expectCompleted = true;
        Task task = new Task("12341231", mockTitle, mockContent, mockTimedControl, true);

        var actualCompleted = task.isCompleted();

        assertEquals(expectCompleted, actualCompleted);
    }

    @Test
    @DisplayName("Should return task title and time left as string")
    void givenTask_whenToString_thenReturnStringWithTaskContentAndTimeLeft() {
        String expectString = "Teste - 3h";

        when(mockTitle.getText()).thenReturn("Teste");
        when(mockTimedControl.getTimeLeft()).thenReturn("3h");

        Task task = new Task(mockTitle, mockContent, mockTimedControl);
        var actualString = task.toString();

        assertEquals(expectString, actualString);
    }
}
