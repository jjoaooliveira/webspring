package com.webapp.usecase.Mapper;

import com.webapp.entity.*;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.mapper.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TaskMapperTest {

    TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        taskMapper = new TaskMapper();
    }

    @Test
    void givenTask_whenToOutputDTO_thenReturnNotNullOutputTaskDTO() {
        //arrange
        UUID expectId = UUID.randomUUID();
        String expectedTitle = "Title 1";
        String expectedContent = "Content 1";
        Boolean expectCompleted = true, expectExpired = true;
        OffsetDateTime expectedCreation = OffsetDateTime.parse("2024-12-24T15:00-03:00");
        OffsetDateTime expectedExpiration = OffsetDateTime.parse("2024-12-25T15:00-03:00");

        Task task = new Task(
                expectId,
                new Title("Title 1"),
                new Content("Content 1"),
                new TimeMark(expectedCreation),
                new TimedMark(expectedExpiration),
                true
        );

        //act
        var actual = taskMapper.toOutputDTO(task);

        //assert
        assertNotNull(actual);
        assertEquals(expectId, actual.id());
        assertEquals(expectedTitle, actual.title());
        assertEquals(expectedContent, actual.content());
        assertEquals(expectedCreation, actual.creation());
        assertEquals(expectedExpiration, actual.expiration());
        assertEquals(expectCompleted, actual.completed());
        assertEquals(expectExpired, actual.expired());
    }

    @Test
    void givenInputTaskDTO_whenToTask_thenReturnNotNullTask() {
        //arrange
        UUID expectId = UUID.randomUUID();
        String expectedTitle = "Title";
        String expectedContent = "Content";
        OffsetDateTime expectedExpiration = OffsetDateTime.parse("2024-12-25T15:00-03:00");

        InputTaskDTO inputTaskDTO = new InputTaskDTO(
                null,
                "Title",
                "Content",
                null,
                expectedExpiration,
                true
        );

        //act
        var actual = taskMapper.toTask(inputTaskDTO);

        //assert
        assertNotNull(actual);
        assertEquals(expectedTitle, actual.getTitle());
        assertEquals(expectedContent, actual.getContent());
        assertEquals(expectedExpiration, actual.getExpiration());
    }
}
