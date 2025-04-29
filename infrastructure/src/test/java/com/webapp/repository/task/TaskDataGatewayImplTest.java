package com.webapp.repository.task;

import com.webapp.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.*;

@EnableAutoConfiguration
@ContextConfiguration(classes = {EntityTaskMapper.class})
@ExtendWith(SpringExtension.class)
@DataJpaTest
class TaskDataGatewayImplTest {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EntityTaskMapper entityTaskMapper;

    TaskDataGatewayImpl taskDataGateway;

    @BeforeEach
    void setUp() {
        taskDataGateway = new TaskDataGatewayImpl(taskRepository, entityTaskMapper);
    }

    @Test
    @DisplayName("Save Task With Success Should Return Task With Correct Fields")
    void givenTask_whenSave_thenReturnTask() {
        //arrange
        Task task = new Task(
                new Title("Title"),
                new Content("Content1"),
                Instant.now(),
                false
        );

        //act
        var actual = taskDataGateway.save(task);

        //assert
        assertNotNull("The actual Task should not be null", actual);
        assertNotNull("The actual Task id should not be null", actual.getId());
        assertEquals("The actual Task title is incorrect", task.getTitle(), actual.getTitle());
        assertEquals("The actual Task content is incorrect", task.getContent(), actual.getContent());
        assertNotNull("The actual Task creation should not be null", actual.getCreation());
        assertEquals("The actual Task expiration is incorrect", task.getExpiration(), actual.getExpiration());
        assertEquals("The actual Task completed is incorrect", task.isCompleted(), actual.isCompleted());
    }

    @Test
    @DisplayName("Should Delete Task")
    void givenTaskId_whenDelete_thenShouldDeleteTask() {
        //arrange
        Task task = new Task(
                new Title("Title"),
                new Content("Content1"),
                Instant.now(),
                false
        );
        Task savedTask = taskDataGateway.save(task);
        UUID uuid = savedTask.getId();

        //act
        taskDataGateway.delete(uuid);
        var actual = taskRepository.existsById(uuid);

        //assert
        assertFalse("Actual should be false", actual);
    }

    @Test
    @DisplayName("FindAll Method Should Return a List of Task")
    void givenTasks_whenFindAll_thenShouldReturnTaskList() {
        //arrange
        Integer expectedSize = 2;
        Task task1 = new Task(
                new Title("Title 1"),
                new Content("Content 1"),
                Instant.now(),
                false
        );

        Task task2 = new Task(
                new Title("Title 2"),
                new Content("Content 2"),
                Instant.now(),
                false
        );
        taskDataGateway.save(task1);
        taskDataGateway.save(task2);

        //act
        var actual = taskDataGateway.findAll();

        //assert
        assertNotNull("Actual list should not be null", actual);
        assertEquals("Actual list size should be one", expectedSize, actual.size());

    }

    @Test
    @DisplayName("Find Task By Title Should Return Task List")
    void givenTitle_whenFindByTitle_thenShouldReturnTaskList() {
        //arrange
        String expectedTitle = "Simple Title";
        Integer expectedSize = 2;
        Task task1 = new Task(
                new Title("Simple Title"),
                new Content("Content 1"),
                Instant.now(),
                false
        );

        Task task2 = new Task(
                new Title("Simple Title"),
                new Content("Content 2"),
                Instant.now(),
                false
        );
        taskDataGateway.save(task1);
        taskDataGateway.save(task2);

        //act
        var actual = taskDataGateway.findByTitle("Simple Title");

        //assert
        assertEquals("The actual task list size should be 2", expectedSize, actual.size());
        assertEquals("The first task Title is incorrect", expectedTitle, actual.getFirst().getTitle());
        assertEquals("The last task Title is incorrect", expectedTitle, actual.getLast().getTitle());
    }

    @Test
    @DisplayName("Find Task By Id Should Return Correct Task")
    void givenTaskId_whenFindById_thenShouldReturnTask() {
        //arrange
        Task task = new Task(
                new Title("Title"),
                new Content("Content"),
                Instant.now(),
                false
        );
        Task savedTask = taskDataGateway.save(task);
        UUID uuid = savedTask.getId();

        //act
        var actual = taskDataGateway.findById(uuid);

        //assert
        assertNotNull("The actual task should not be null", actual);
        assertNotNull("The actual task id should not be null", actual.getId());
        assertEquals("The actual task title is incorrect", task.getTitle(), actual.getTitle());
        assertEquals("The actual task content is incorrect", task.getContent(), actual.getContent());
        assertNotNull("The actual task creation date should not be null", actual.getCreation());
        assertEquals("The actual task expiration is incorrect", task.getExpiration(), actual.getExpiration());
        assertEquals("The actual task completed is incorrect", task.isCompleted(), actual.isCompleted());

    }
}
