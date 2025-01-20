package Mapper;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TaskMapperTest {
    @Mock
    Task mockTask;

    @Mock
    InputTaskDTO mockInputTaskDTO;

    @InjectMocks
    TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenTask_whenToOutputDTO_thenReturnNotNullOutputTaskDTO() {
        String expectedTitle = "mockTitle";
        String expectedContent = "mockContent";
        String expectedId = "12314324";
        Boolean expectCompleted = true, expectExpired = true;

        when(mockTask.getId()).thenReturn("12314324");
        when(mockTask.getTitle()).thenReturn("mockTitle");
        when(mockTask.getContent()).thenReturn("mockContent");
        when(mockTask.getTimeLeft()).thenReturn("mockTimeLeft");
        when(mockTask.isCompleted()).thenReturn(true);
        when(mockTask.isExpired()).thenReturn(true);

        var actualOutputTaskDTO = taskMapper.toOutputDTO(mockTask);
        assertNotNull(actualOutputTaskDTO);
        assertEquals(expectedId, actualOutputTaskDTO.id());
        assertEquals(expectedTitle, actualOutputTaskDTO.title());
        assertEquals(expectedContent, actualOutputTaskDTO.content());
        assertEquals(expectCompleted, actualOutputTaskDTO.completed());
        assertEquals(expectExpired, actualOutputTaskDTO.expired());
    }

    @Test
    void givenInputTaskDTO_whenToTask_thenReturnNotNullTask() throws TextLengthOverLimitException, EmptyTextException {
        String expectedTitle = "mockTitle";
        String expectedContent = "mockContent";
        String expectExpiration = "2024-12-25T15:00-03:00";
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2024-12-25T15:00-03:00");

        when(mockInputTaskDTO.title()).thenReturn("mockTitle");
        when(mockInputTaskDTO.content()).thenReturn("mockContent");
        when(mockInputTaskDTO.expirationDate()).thenReturn(offsetDateTime);

        var actualTask = taskMapper.toTask(mockInputTaskDTO);

        assertNotNull(actualTask);
        assertEquals(expectedTitle, actualTask.getTitle());
        assertEquals(expectedContent, actualTask.getContent());
        assertEquals(expectExpiration, actualTask.getExpiration().toString());
    }
}
