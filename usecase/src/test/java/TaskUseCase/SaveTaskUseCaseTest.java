package TaskUseCase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.exception.FailToCreateTaskException;
import com.webapp.usecase.mapper.TaskMapper;
import com.webapp.usecase.task.SaveTaskUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SaveTaskUseCaseTest {
    @Mock
    TaskDataAccess mockTaskDataAccess;
    @Mock
    TaskMapper mockTaskMapper;
    @Mock
    InputTaskDTO mockInputTaskDTO;
    @Mock
    Task mockTask;
    @Mock
    Task mockReturnedTask;
    @Mock
    OutputTaskDTO mockOutputTaskDTO;
    @InjectMocks
    SaveTaskUseCase saveTaskUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenSaveTaskUseCase_whenExecute_thenReturnNotNullSavedObject() throws TextLengthOverLimitException, EmptyTextException {

        when(mockTaskMapper.toTask(mockInputTaskDTO)).thenReturn(mockTask);
        when(mockTaskDataAccess.save(mockTask)).thenReturn(mockReturnedTask);
        when(mockTaskMapper.toOutputDTO(mockReturnedTask)).thenReturn(mockOutputTaskDTO);

        OutputTaskDTO actualOutputTaskDTO = saveTaskUseCase.execute(mockInputTaskDTO);
        assertNotNull(actualOutputTaskDTO);
    }

    @Test
    void givenSaveTaskUseCase_whenThrowTextLengthOverLimitException_thenThrowFailToCreateTaskException() throws TextLengthOverLimitException, EmptyTextException {

        when(mockTaskMapper.toTask(mockInputTaskDTO)).thenThrow(TextLengthOverLimitException.class);

        assertThrows(FailToCreateTaskException.class, () -> saveTaskUseCase.execute(mockInputTaskDTO));
    }

    @Test
    void givenSaveTaskUseCase_whenThrowEmptyTextException_thenThrowFailToCreateTaskException() throws TextLengthOverLimitException, EmptyTextException {

        when(mockTaskMapper.toTask(mockInputTaskDTO)).thenThrow(EmptyTextException.class);

        assertThrows(FailToCreateTaskException.class, () -> saveTaskUseCase.execute(mockInputTaskDTO));
    }
}
