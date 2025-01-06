package TaskUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dataaccess.TaskRepository;
import com.webapp.usecase.dto.task.DatabaseTaskDTO;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.DatabaseMapper;
import com.webapp.usecase.mapper.TaskMapper;
import com.webapp.usecase.task.SaveTaskUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SaveTaskUseCaseTest {
    @Mock
    TaskRepository mockTaskRepository;
    @Mock
    TaskMapper mockTaskMapper;
    @Mock
    DatabaseMapper mockDatabaseMapper;
    @Mock
    InputTaskDTO mockTaskDTO;
    @Mock
    Task mockTask;
    @Mock
    DatabaseTaskDTO mockInputDatabaseTaskDTO;
    @Mock
    DatabaseTaskDTO mockOutputDatabaseTaskDTO;
    @Mock
    Task mockResponseTask;
    @Mock
    OutputTaskDTO mockOutputTaskDTO;
    @InjectMocks
    SaveTaskUseCase saveTaskUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenInputObject_whenExecute_thenReturnSavedObject() throws TextLengthOverLimitException, EmptyTextException {

        when(mockTaskMapper.createEntity(mockTaskDTO)).thenReturn(mockTask);
        when(mockDatabaseMapper.createDTO(mockTask)).thenReturn(mockInputDatabaseTaskDTO);
        when(mockTaskRepository.save(mockInputDatabaseTaskDTO)).thenReturn(mockOutputDatabaseTaskDTO);
        when(mockDatabaseMapper.createEntity(mockOutputDatabaseTaskDTO)).thenReturn(mockResponseTask);
        when(mockTaskMapper.createDTO(mockResponseTask)).thenReturn(mockOutputTaskDTO);

        OutputTaskDTO actualReturn = saveTaskUseCase.execute(mockTaskDTO);
        assertNotNull(actualReturn);
    }
}
