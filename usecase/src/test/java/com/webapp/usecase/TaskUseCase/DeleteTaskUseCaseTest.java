package com.webapp.usecase.TaskUseCase;

import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.task.DeleteTaskUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteTaskUseCaseTest {
    @Mock
    TaskDataAccess mockTaskDataAccess;

    UseCaseAPI useCaseAPI;

    UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        useCaseAPI = new UseCaseAPI();
    }

    @Test
    void givenALongValue_whenExecute_thenCallRepositoryMethodDeleteOneTime() {
        useCaseAPI.deleteTask(id, mockTaskDataAccess);

        verify(mockTaskDataAccess, times(1)).delete(id);
    }
}
