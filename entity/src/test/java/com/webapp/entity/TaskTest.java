package com.webapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

public class TaskTest {

    @Test
    @DisplayName("Create a Task With Valid Inputs Should Return Task With Valid Fields")
    void givenValidArguments_whenNewTask_thenReturnTaskWithValidFields() {
        //arrange
        UUID uuid = UUID.randomUUID();
        Title title = new Title("Title");
        Content content = new Content("Content");
        Instant creationDate = Instant.parse("2024-12-31T00:00:00Z");
        Instant expirationDate = Instant.parse("2025-01-01T00:00:00Z");
        Boolean isCompleted = false;

        String expectedTitle = "Title";
        String expectedContent = "Content";
        String expectedCreation = "2024-12-31T00:00:00Z";
        String expectedExpiration = "2025-01-01T00:00:00Z";
        Boolean expectedCompleted = false;

        //act
        var actual = new Task(
                uuid,
                title,
                content,
                creationDate,
                expirationDate,
                isCompleted
        );

        //assert
        assertEquals(uuid, actual.getId(), "The task id is incorrect");
        assertEquals(expectedTitle, actual.getTitle(), "The task title is incorrect");
        assertEquals(expectedContent, actual.getContent(), "The task content is incorrect");
        assertEquals(expectedCreation, actual.getCreation().toString(), "The task creation date is incorrect");
        assertEquals(expectedExpiration, actual.getExpiration().toString(), "The task expiration date is incorrect");
        assertEquals(expectedCompleted, actual.isCompleted(), "The task completed is incorrect");
    }
}
