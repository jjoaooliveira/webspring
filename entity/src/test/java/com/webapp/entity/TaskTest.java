package com.webapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.webapp.entity.api.TaskAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public class TaskTest {
    String title;

    String content;

    OffsetDateTime timeMark;

    OffsetDateTime timedMark;

    Boolean isCompleted;

    @BeforeEach
    void setUp() {
        title = "Title 1";
        content = "Content 1";
        timeMark = OffsetDateTime.parse("2024-12-31T00:00-03:00");
        timedMark = OffsetDateTime.parse("2025-01-01T00:00-03:00");
        isCompleted = true;
    }

    @Test
    @DisplayName("When Creating a Task With Success Should Contains Valid Fields")
    void givenTask_whenNewTask_thenReturnTask() {
        //arrange
        UUID uuid = UUID.randomUUID();
        String expectedTitle = "Title 1";
        String expectedContent = "Content 1";
        String expectedCreation = "2024-12-31T00:00-03:00";
        String expectedExpiration = "2025-01-01T00:00-03:00";
        Boolean expectedCompleted = false;

        //act
        var actual = new TaskAPI(
                uuid,
                title,
                content,
                timeMark,
                timedMark,
                false
        );

        //assert
        assertNotNull(actual, "The task should not be null");
        assertEquals(uuid, actual.getId(), "The task id is incorrect");
        assertEquals(expectedTitle, actual.getTitle(), "The task title is incorrect");
        assertEquals(expectedContent, actual.getContent(), "The task content is incorrect");
        assertEquals(expectedCreation, actual.getCreation().toString(), "The task creation is incorrect");
        assertEquals(expectedExpiration, actual.getExpiration().toString(), "The task expiration is incorrect");
        assertEquals(expectedCompleted, actual.taskIsCompleted(), "The task completed is incorrect");
    }

    @Test
    @DisplayName("When Creating Completed Task With Success Should Return Valid Task")
    void givenCompletedTask_whenIsCompleted_thenReturnTrue() {
        //arrange

        //act
        var actual = new TaskAPI(
                title,
                content,
                timeMark,
                timedMark,
                true
        );

        //assert
        assertEquals(true, actual.taskIsCompleted());
    }
}
