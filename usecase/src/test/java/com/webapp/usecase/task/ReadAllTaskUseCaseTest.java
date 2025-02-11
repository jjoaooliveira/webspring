package com.webapp.usecase.task;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.webapp.entity.*;
import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.TaskDataAccess;
import com.webapp.usecase.mapper.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    UseCaseAPI useCaseAPI;

    @BeforeEach
    void setUp() {
        useCaseAPI = new UseCaseAPI();
        taskMapper = new TaskMapper();
    }

    @Test
    @DisplayName("When Execute With Success Should Call FindAll Method and Return Task List")
    void givenTaskList_whenExecute_thenShouldCallFindAllOneTimeMethodAndReturnDTOList() {
        //arrange
        Integer expectSize = 2;
        Task task1 = new Task(
                UUID.randomUUID(),
                new Title("Title 1"),
                new Content("Content 1"),
                new TimeMark(null),
                new TimedMark(OffsetDateTime.now()),
                true
        );
        Task task2 = new Task(
                UUID.randomUUID(),
                new Title("Title 2"),
                new Content("Content 2"),
                new TimeMark(null),
                new TimedMark(OffsetDateTime.now()),
                false
        );
        List<Task> dbTaskList = List.of(task1, task2);
        when(mockTaskDataAccess.findAll()).thenReturn(dbTaskList);

        //act
        var actual = useCaseAPI.readAllTask(taskMapper, mockTaskDataAccess);

        //assert
        verify(mockTaskDataAccess, times(1)
                .description("Should call findAll method only one time"))
                .findAll();
        assertEquals(expectSize, actual.size());
    }
}
