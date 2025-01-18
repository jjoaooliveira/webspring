package TaskUseCase;

import com.webapp.usecase.data_access.TaskRepository;
import com.webapp.usecase.task.DeleteTaskUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteTaskUseCaseTest {
    @Mock
    TaskRepository mockTaskRepository;
    @InjectMocks
    DeleteTaskUseCase deleteTaskUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenALongValue_whenExecute_thenCallRepositoryMethodDeleteOneTime() {
        String id = "23242512";
        deleteTaskUseCase.execute(id);
        verify(mockTaskRepository, times(1)).delete(id);
    }
}
