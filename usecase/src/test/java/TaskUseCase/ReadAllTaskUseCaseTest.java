package TaskUseCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dataaccess.TaskRepository;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.dto.task.TaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import com.webapp.usecase.task.ReadAllTaskUseCase;
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
    Task mockTask1;

    @Mock
    Task mockTask2;

    @Mock
    TaskDTO mockTaskDTO1;

    @Mock
    TaskDTO mockTaskDTO2;

    @Mock
    OutputTaskDTO mockOutputTaskDTO1;

    @Mock
    OutputTaskDTO mockOutputTaskDTO2;

    @InjectMocks
    ReadAllTaskUseCase readAllTaskUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenExecute_thenReturnListOfOutputDTOWithSizeEquals2() throws TextLengthOverLimitException, EmptyTextException {
        List<TaskDTO> dbTaskList = List.of(mockTaskDTO1, mockTaskDTO2);

        when(mockTaskRepository.findAll()).thenReturn(dbTaskList);
        when(mockTaskMapper.toTask(mockTaskDTO1)).thenReturn(mockTask1);
        when(mockTaskMapper.toTask(mockTaskDTO2)).thenReturn(mockTask2);
        when(mockTaskMapper.toOutputDTO(mockTask1)).thenReturn(mockOutputTaskDTO1);
        when(mockTaskMapper.toOutputDTO(mockTask2)).thenReturn(mockOutputTaskDTO2);

        List<OutputTaskDTO> actualTaskDTOList = readAllTaskUseCase.execute();

        assertEquals(2, actualTaskDTOList.size());
    }

    @Test
    void whenExecute_thenReturnEmptyList() {
        //TODO terminar
        List<OutputTaskDTO> actualTaskDTOList = readAllTaskUseCase.execute();
        verify(mockTaskRepository, times(1)).findAll();
        assertTrue(actualTaskDTOList.isEmpty());
    }
}
