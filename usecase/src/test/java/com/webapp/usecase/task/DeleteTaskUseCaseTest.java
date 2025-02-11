package com.webapp.usecase.task;

import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.TaskDataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteTaskUseCaseTest {
    @Mock
    TaskDataAccess mockTaskDataAccess;

    UseCaseAPI useCaseAPI;

    @BeforeEach
    void setUp() {
        useCaseAPI = new UseCaseAPI();
    }

    @Test
    @DisplayName("When Execute With Success Should Call Delete Method")
    void givenId_whenExecute_thenCallRepositoryMethodDeleteOneTime() {
        //arrange
        UUID id = UUID.randomUUID();

        //act
        useCaseAPI.deleteTask(id, mockTaskDataAccess);

        //assert
        verify(mockTaskDataAccess, times(1)
                .description("Should call delete method only one time"))
                .delete(id);

    }
}
