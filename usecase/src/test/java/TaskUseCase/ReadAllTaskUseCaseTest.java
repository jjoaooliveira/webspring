package TaskUseCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dataaccess.TaskRepository;
import com.webapp.usecase.dto.task.DatabaseTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.DatabaseMapper;
import com.webapp.usecase.mapper.TaskMapper;
import com.webapp.usecase.task.ReadAllTaskReturnUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class ReadAllTaskUseCaseTest {

    @Mock
    TaskRepository mockTaskRepository;
    @Mock
    TaskMapper mockTaskMapper;
    @Mock
    DatabaseMapper mockDatabaseMapper;
    @Mock
    Task mockTask1;
    @Mock
    Task mockTask2;
    @Mock
    DatabaseTaskDTO mockDatabaseTaskDTO1;
    @Mock
    DatabaseTaskDTO mockDatabaseTaskDTO2;
    @Mock
    OutputTaskDTO mockOutputTaskDTO1;
    @Mock
    OutputTaskDTO mockOutputTaskDTO2;
    @InjectMocks
    ReadAllTaskReturnUseCase readAllTaskUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenExecute_thenReturnListOfOutputDTOWithSizeEquals2() throws TextLengthOverLimitException, EmptyTextException {
        List<DatabaseTaskDTO> dbTaskList = List.of(mockDatabaseTaskDTO1, mockDatabaseTaskDTO2);

        when(mockTaskRepository.findAll()).thenReturn(dbTaskList);
        when(mockDatabaseMapper.createEntity(mockDatabaseTaskDTO1)).thenReturn(mockTask1);
        when(mockDatabaseMapper.createEntity(mockDatabaseTaskDTO2)).thenReturn(mockTask2);
        when(mockTaskMapper.createDTO(mockTask1)).thenReturn(mockOutputTaskDTO1);
        when(mockTaskMapper.createDTO(mockTask2)).thenReturn(mockOutputTaskDTO2);

        List<OutputTaskDTO> actualTaskDTOList = readAllTaskUseCase.execute();

        assertEquals(2, actualTaskDTOList.size());
    }

    @Test
    void whenExecute_thenReturnEmptyList() throws TextLengthOverLimitException, EmptyTextException {
        List<DatabaseTaskDTO> dbTaskList = List.of();

        when(mockTaskRepository.findAll()).thenReturn(dbTaskList);
        when(mockDatabaseMapper.createEntity(mockDatabaseTaskDTO1)).thenReturn(mockTask1);
        when(mockDatabaseMapper.createEntity(mockDatabaseTaskDTO2)).thenReturn(mockTask2);
        when(mockTaskMapper.createDTO(mockTask1)).thenReturn(mockOutputTaskDTO1);
        when(mockTaskMapper.createDTO(mockTask2)).thenReturn(mockOutputTaskDTO2);

        List<OutputTaskDTO> actualTaskDTOList = readAllTaskUseCase.execute();

        assertTrue(actualTaskDTOList.isEmpty());
    }
}
