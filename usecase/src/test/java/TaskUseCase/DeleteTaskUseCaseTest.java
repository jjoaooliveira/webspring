package TaskUseCase;

import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.task.DeleteTaskUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteTaskUseCaseTest {
    @Mock
    TaskDataAccess mockTaskDataAccess;
    @InjectMocks
    DeleteTaskUseCase deleteTaskUseCase;

    UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenALongValue_whenExecute_thenCallRepositoryMethodDeleteOneTime() {
        deleteTaskUseCase.execute(id);
        verify(mockTaskDataAccess, times(1)).delete(id);
    }
}
