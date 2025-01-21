package TaskUseCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.dto.task.OutputTaskDTO;
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
    TaskDataAccess mockTaskDataAccess;

    @Mock
    TaskMapper mockTaskMapper;

    @Mock
    Task mockTask1;

    @Mock
    Task mockTask2;

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
    void givenReadAllTaskUseCase_whenExecute_thenReturnOutputDTOListWithSizeEquals2() throws TextLengthOverLimitException, EmptyTextException {
        List<Task> dbTaskList = List.of(mockTask1, mockTask2);
        Integer expectSize = 2;

        when(mockTaskDataAccess.findAll()).thenReturn(dbTaskList);
        when(mockTaskMapper.toOutputDTO(mockTask1)).thenReturn(mockOutputTaskDTO1);
        when(mockTaskMapper.toOutputDTO(mockTask2)).thenReturn(mockOutputTaskDTO2);

        List<OutputTaskDTO> actualTaskDTOList = readAllTaskUseCase.execute();

        assertEquals(expectSize, actualTaskDTOList.size());
    }
}
