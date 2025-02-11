package com.webapp.usecase.task;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.webapp.entity.*;
import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SaveTaskUseCaseTest {
    @Mock
    TaskDataAccess mockTaskDataAccess;

    TaskMapper taskMapper;

    UseCaseAPI useCaseAPI;

    @BeforeEach
    void setUp() {
        useCaseAPI = new UseCaseAPI();
        taskMapper = new TaskMapper();
    }

    @Test
    @DisplayName("When Execute With Success Should Call Save Method and Return Saved Task")
    void givenInputTaskDTO_whenExecute_thenShouldCallSaveMethodOneTimeAndReturnSavedTask() {
        //arrange
        OffsetDateTime expiration = OffsetDateTime.now();

        InputTaskDTO inputTaskDTO = new InputTaskDTO(
                null,
                "Title 1",
                "Content 1",
                null,
                expiration,
                true
        );

        Task returnedTask = new Task(
                UUID.randomUUID(),
                new Title("Title 1"),
                new Content("Content 1"),
                new TimeMark(OffsetDateTime.now()),
                new TimedMark(expiration),
                true
        );
        when(mockTaskDataAccess.save(any(Task.class))).thenReturn(returnedTask);

        //act
        var actual = useCaseAPI.saveTask(inputTaskDTO, taskMapper, mockTaskDataAccess);

        //assert
        verify(mockTaskDataAccess, times(1)
                .description("Should call only one time save method"))
                .save(any(Task.class));
        assertNotNull(actual, "The actual task should not be null");
    }
}
