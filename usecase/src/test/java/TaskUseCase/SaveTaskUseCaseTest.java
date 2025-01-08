package TaskUseCase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.webapp.entity.Task;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dataaccess.TaskRepository;
import com.webapp.usecase.dto.task.NewTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.dto.task.TaskDTO;
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
    NewTaskDTO mockNewTaskDTO;
    @Mock
    Task mockTask;
    @Mock
    Task mockReturnedTask;
    @Mock
    TaskDTO mockTaskDTO;
    @Mock
    TaskDTO mockReturnedTaskDTO;
    @Mock
    OutputTaskDTO mockOutputTaskDTO;
    @InjectMocks
    SaveTaskUseCase saveTaskUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenExecute_thenReturnNotNullSavedObject() throws TextLengthOverLimitException, EmptyTextException {

        when(mockTaskMapper.toNewTask(mockNewTaskDTO)).thenReturn(mockTask);
        when(mockTaskMapper.toTaskDTO(mockTask)).thenReturn(mockTaskDTO);
        when(mockTaskRepository.save(mockTaskDTO)).thenReturn(mockReturnedTaskDTO);
        when(mockTaskMapper.toTask(mockReturnedTaskDTO)).thenReturn(mockReturnedTask);
        when(mockTaskMapper.toOutputDTO(mockReturnedTask)).thenReturn(mockOutputTaskDTO);

        OutputTaskDTO actualOutputTaskDTO = saveTaskUseCase.execute(mockNewTaskDTO);
        assertNotNull(actualOutputTaskDTO);
    }
}
