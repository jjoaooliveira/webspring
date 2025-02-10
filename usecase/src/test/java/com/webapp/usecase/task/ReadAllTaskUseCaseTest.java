package com.webapp.usecase.TaskUseCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.webapp.entity.*;
import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.mapper.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ReadAllTaskUseCaseTest {

    @Mock
    TaskDataAccess mockTaskDataAccess;

    TaskMapper taskMapper;

    Task task1, task2;

    UseCaseAPI useCaseAPI;

    @BeforeEach
    void setUp() {
        useCaseAPI = new UseCaseAPI();
        taskMapper = new TaskMapper();
        task1 = new Task(
                UUID.randomUUID(),
                new Title("Title 1"),
                new Content("Content 1"),
                new TimeMark(null),
                new TimedMark(OffsetDateTime.now()),
                true
        );
        task2 = new Task(
                UUID.randomUUID(),
                new Title("Title 2"),
                new Content("Content 2"),
                new TimeMark(null),
                new TimedMark(OffsetDateTime.now()),
                false
        );
    }

    @Test
    void givenReadAllTaskUseCase_whenExecute_thenReturnOutputDTOListWithSizeEquals2() {
        //arrange
        List<Task> dbTaskList = List.of(task1, task2);
        Integer expectSize = 2;

        when(mockTaskDataAccess.findAll()).thenReturn(dbTaskList);

        //act
        var actual = useCaseAPI.readAllTask(taskMapper, mockTaskDataAccess);

        //assert
        assertEquals(expectSize, actual.size());
        assertEquals(task1.getId(), actual.get(0).id());
        assertEquals(task2.getId(), actual.get(1).id());
    }
}
