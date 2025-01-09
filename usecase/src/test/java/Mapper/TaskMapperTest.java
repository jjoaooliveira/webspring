package Mapper;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dto.task.NewTaskDTO;
import com.webapp.usecase.dto.task.TaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TaskMapperTest {
    @Mock
    Task mockTask;

    @Mock
    TaskDTO mockTaskDTO;

    @Mock
    NewTaskDTO mockNewTaskDTO;

    @Mock
    ZonedDateTime mockZonedDateTime;

    @Mock
    LocalDate mockLocalDate;

    @Mock
    LocalTime mockLocalTime;

    @InjectMocks
    TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenTaskInput_whenToOutputDTO_thenReturnNotNullOutputTaskDTO() {
        String expectedTitle = "mockTitle";
        String expectedContent = "mockContent";
        long expectedId = 1L;
        boolean mockCompleted = true, mockExpired = true;

        when(mockTask.getId()).thenReturn(1L);
        when(mockTask.getTitle()).thenReturn("mockTitle");
        when(mockTask.getContent()).thenReturn("mockContent");
        when(mockTask.getCreationDate()).thenReturn(mockZonedDateTime);
        when(mockTask.getExpirationDate()).thenReturn(mockZonedDateTime);
        when(mockZonedDateTime.toLocalDate()).thenReturn(mockLocalDate);
        when(mockZonedDateTime.toLocalTime()).thenReturn(mockLocalTime);
        when(mockLocalDate.toString()).thenReturn("mockLocalDate");
        when(mockLocalTime.toString()).thenReturn("mockLocalTime");
        when(mockTask.getTimeLeft()).thenReturn("mockTimeLeft");
        when(mockTask.isCompleted()).thenReturn(mockCompleted);
        when(mockTask.isExpired()).thenReturn(mockExpired);

        var actualOutputTaskDTO = taskMapper.toOutputDTO(mockTask);
        assertNotNull(actualOutputTaskDTO);
        assertEquals(expectedId, actualOutputTaskDTO.id());
        assertEquals(expectedTitle, actualOutputTaskDTO.title());
        assertEquals(expectedContent, actualOutputTaskDTO.content());
    }

    @Test
    void givenTask_whenToTaskDTO_thenReturnNotNullTaskDTO() {
        String expectedTitle = "mockTitle";
        String expectedContent = "mockContent";
        long expectedId = 1L;
        boolean mockCompleted = true;

        when(mockTask.getId()).thenReturn(1L);
        when(mockTask.getTitle()).thenReturn("mockTitle");
        when(mockTask.getContent()).thenReturn("mockContent");
        when(mockTask.getCreationDate()).thenReturn(mockZonedDateTime);
        when(mockTask.getExpirationDate()).thenReturn(mockZonedDateTime);
        when(mockZonedDateTime.toString()).thenReturn("mockdate");
        when(mockTask.isCompleted()).thenReturn(mockCompleted);

        var actualTaskDTO = taskMapper.toTaskDTO(mockTask);
        assertNotNull(actualTaskDTO);
        assertEquals(expectedId, actualTaskDTO.id());
        assertEquals(expectedTitle, actualTaskDTO.title());
        assertEquals(expectedContent, actualTaskDTO.content());
        assertTrue(actualTaskDTO.completed());
    }

    @Test
    void givenTaskDTO_whenToTask_thenReturnNotNullTask() throws TextLengthOverLimitException, EmptyTextException {
        String expectedTitle = "mockTitle";
        String expectedContent = "mockContent";
        String expectDateTime = "2024-12-25T15:00";

        when(mockTaskDTO.title()).thenReturn("mockTitle");
        when(mockTaskDTO.content()).thenReturn("mockContent");
        when(mockTaskDTO.expirationDate()).thenReturn("2024-12-25T15:00");

        var actualTaskDTO = taskMapper.toTask(mockTaskDTO);
        assertNotNull(actualTaskDTO);
        assertEquals(expectedTitle, actualTaskDTO.getTitle());
        assertEquals(expectedContent, actualTaskDTO.getContent());
        assertEquals(expectDateTime, actualTaskDTO.getExpirationDate().toLocalDateTime().toString());
    }

    @Test
    void givenNewTaskDTO_whenToNewTask_thenReturnNotNullTask() throws TextLengthOverLimitException, EmptyTextException {
        String expectTitle = "mockTitle";
        String expectContent = "mockContent";
        String expectExpirationDateTime = "2024-12-25T15:00";

        when(mockNewTaskDTO.title()).thenReturn("mockTitle");
        when(mockNewTaskDTO.content()).thenReturn("mockContent");
        when(mockNewTaskDTO.expirationDate()).thenReturn("2024-12-25T15:00");

        var actualTask = taskMapper.toNewTask(mockNewTaskDTO);
        assertNotNull(actualTask);
        assertEquals(expectTitle, actualTask.getTitle());
        assertEquals(expectContent, actualTask.getContent());
        assertEquals(expectExpirationDateTime, actualTask.getExpirationDate().toLocalDateTime().toString());
    }

}
