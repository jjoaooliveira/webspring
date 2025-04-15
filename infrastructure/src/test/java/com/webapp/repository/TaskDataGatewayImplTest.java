package com.webapp.repository;

import com.webapp.entity.*;
import com.webapp.repository.api.TaskRepositoryAPI;
import com.webapp.repository.entity.TaskEntity;
import com.webapp.repository.mapper.EntityTaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@EnableAutoConfiguration
@ContextConfiguration(classes = {
        TaskRepository.class,
        TaskEntity.class,
        EntityTaskMapper.class
})
@ExtendWith(SpringExtension.class)
@DataJpaTest
class TaskDataAccessImplTest {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EntityTaskMapper entityTaskMapper;

    TaskRepositoryAPI taskRepositoryAPI;

    @BeforeEach
    void setUp() {
        taskRepositoryAPI = new TaskRepositoryAPI(taskRepository, entityTaskMapper);
    }

    @Test
    @DisplayName("When Save Task With Success Should Return Valid Task")
    void givenTask_whenSave_thenReturnTask() {
        //arrange
        Task task = new Task(
                null,
                new Title("Title"),
                new Content("Content1"),
                new TimeMark(null),
                new TimedMark(ZonedDateTime.now()),
                true
        );

        //act
        var actual = taskRepositoryAPI.taskDataAccessSave(task);

        //assert
        assertNotNull("The actual Task should not be null", actual);
        assertEquals("The actual Task Title is incorrect", task.getTitle(), actual.getTitle());
        assertEquals("The actual Task Content is incorrect", task.getContent(), actual.getContent());
        assertEquals("The actual Task Creation is incorrect", task.getCreation(), actual.getCreation());
        assertEquals("The actual Task Expiration is incorrect", task.getExpiration(), actual.getExpiration());
        assertEquals("The actual Task Completed is incorrect", task.isCompleted(), actual.isCompleted());
    }

    @Test
    @DisplayName("When Delete Task With Success Should Delete Task")
    void givenTaskId_whenDelete_thenShouldDeleteTask() {
        //arrange
        TaskEntity entity = new TaskEntity();
        entity.setUUID(UUID.randomUUID());
        entity.setTitle("Title 1");
        entity.setContent("Content 1");
        entity.setCreation(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
        taskRepository.save(entity);
        UUID uuid = entity.getUUID();

        //act
        taskRepositoryAPI.taskDataAccessDelete(uuid);
        var actual = taskRepositoryAPI.taskDataAccessFindAll();

        //assert
        assertEquals("Actual list size should be zero", 0, actual.size());
    }

    @Test
    @DisplayName("When FindAll Method Should Return a List of Task")
    void givenTaskEntity_whenFindAll_thenShouldReturnTaskList() {
        //arrange
        Integer expectedSize = 1;
        TaskEntity entity = new TaskEntity();
        entity.setUUID(UUID.randomUUID());
        entity.setTitle("Title 1");
        entity.setContent("Content 1");
        entity.setCreation(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
        entity.setExpiration(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
        entity.setCompleted(true);
        taskRepository.save(entity);

        //act
        var actual = taskRepositoryAPI.taskDataAccessFindAll();

        //assert
        assertNotNull("Actual list should not be null", actual);
        assertEquals("Actual list size should be one", expectedSize, actual.size());

    }

    @Test
    @DisplayName("When Find Task By Title With Success Should Return Task List")
    void givenTitle_whenFindByTitle_thenShouldReturnTaskList() {
        //arrange
        String expectedTitle = "Simple Title";
        Integer expectedSize = 2;
        TaskEntity taskEntity1 = new TaskEntity(
                UUID.randomUUID(),
                "Simple Title",
                "Content",
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                true
        );

        TaskEntity taskEntity2 = new TaskEntity(
                UUID.randomUUID(),
                "Simple Title",
                "Content",
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                false
        );
        taskRepository.save(taskEntity1);
        taskRepository.save(taskEntity2);

        //act
        var actual = taskRepositoryAPI.taskDataAccessFindByTitle("Simple Title");

        //assert
        assertNotNull("The actual list should not be null", actual);
        assertEquals("The actual list size should be 2", expectedSize, actual.size());
        assertEquals("The Title is incorrect", expectedTitle, actual.getFirst().getTitle());
        assertEquals("The Title is incorrect", expectedTitle, actual.getLast().getTitle());
    }

    @Test
    @DisplayName("When Find Task By Id With Success Should Return Correct Task")
    void givenTaskId_whenFindById_thenShouldReturnTask() {
        //arrange
        String expectedTitle = "Title";
        String expectedContent = "Content";
        OffsetDateTime expectedCreation = OffsetDateTime.parse("2025-01-01T00:00:00-03:00");
        OffsetDateTime expectedExpiration = OffsetDateTime.parse("2025-01-01T01:00:00-03:00");
        Boolean expectedCompleted = false;
        UUID uuid = UUID.randomUUID();

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUUID(uuid);
        taskEntity.setTitle("Title");
        taskEntity.setContent("Content");
        taskEntity.setCreation(OffsetDateTime.parse("2025-01-01T00:00:00-03:00"));
        taskEntity.setExpiration(OffsetDateTime.parse("2025-01-01T01:00:00-03:00"));
        taskEntity.setCompleted(false);
        taskRepository.save(taskEntity);

        //act
        var actual = taskRepositoryAPI.taskDataAccessFindById(uuid);

        //assert
        assertNotNull("The actual task should not be null", actual);
        assertEquals("The Title is incorrect", expectedTitle, actual.getTitle());
        assertEquals("The Content is incorrect", expectedContent, actual.getContent());
        assertEquals("The Creation is incorrect", expectedCreation, actual.getCreation());
        assertEquals("The Expiration is incorrect", expectedExpiration, actual.getExpiration());
        assertEquals("The Completed is incorrect", expectedCompleted, actual.isCompleted());

    }
}
