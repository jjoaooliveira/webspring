package com.webapp.presenter;

import static org.junit.jupiter.api.Assertions.*;

import com.webapp.usecase.task.TaskOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.time.Instant;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.Random.class)
@DisplayName("Task Web Presenter Test")
public class TaskWebPresenterTest {

    TaskWebPresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new TaskWebPresenter();
    }

    @Test
    @DisplayName("Presenter toEntity Method Should Return Correct EntityModel Object")
    void givenTaskOutputData_whenToEntity_thenShouldReturnCorrectEntityModel() {
        //arrange
        TaskOutputData taskOutputData = new TaskOutputData(
                UUID.randomUUID(),
                "title",
                "content",
                Instant.now().toString(),
                Instant.now().toString(),
                false
        );

        //act
        var actual = presenter.toEntityModel(taskOutputData);

        //assert
        assertEquals(taskOutputData, actual.getContent());
    }

    @Test
    @DisplayName("Presenter toEntity Method Should Return Correct EntityModel Object With Valid Content")
    void givenTaskOutputData_whenToEntity_thenShouldReturnCorrectEntityModelWithValidContent() {
        //arrange
        TaskOutputData taskOutputData = new TaskOutputData(
                UUID.randomUUID(),
                "title",
                "content",
                Instant.now().toString(),
                Instant.now().toString(),
                false
        );

        //act
        var actual = presenter.toEntityModel(taskOutputData);

        //assert
        assertNotNull(actual.getContent());
        assertEquals(taskOutputData.getId(), actual.getContent().getId());
        assertEquals(taskOutputData.getTitle(), actual.getContent().getTitle());
        assertEquals(taskOutputData.getContent(), actual.getContent().getContent());
        assertEquals(taskOutputData.getCreationDate(), actual.getContent().getCreationDate());
        assertEquals(taskOutputData.getExpirationDate(), actual.getContent().getExpirationDate());
        assertEquals(taskOutputData.isCompleted(), actual.getContent().isCompleted());
    }
}