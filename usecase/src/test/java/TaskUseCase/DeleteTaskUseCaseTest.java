package TaskUseCase;

import com.webapp.usecase.dataaccess.TaskRepository;
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
        long aLong = 1L;
        deleteTaskUseCase.execute(aLong);
        verify(mockTaskRepository, times(1)).delete(aLong);
    }
}
