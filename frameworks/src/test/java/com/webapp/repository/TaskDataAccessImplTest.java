package com.webapp.repository;

import com.webapp.entity.*;
import com.webapp.repository.api.TaskRepositoryAPI;
import com.webapp.repository.entity.TaskEntity;
import com.webapp.repository.mapper.TaskMapper;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@EnableAutoConfiguration
@ContextConfiguration(classes = {
        TaskRepository.class,
        TaskEntity.class,
        TaskMapper.class
})
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TaskDataAccessImplTest {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskMapper taskMapper;

    TaskRepositoryAPI taskRepositoryAPI;

    @BeforeEach
    void setUp() {
        taskRepositoryAPI = new TaskRepositoryAPI(taskRepository, taskMapper);
    }

    @Test
    @DisplayName("When Save Task Should Return Task With Valid Fields")
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
    @DisplayName("When Delete Task Should Call Delete Method One Time")
    void givenTaskDataAccess_whenDelete_thenShouldCallRepositoryDelete() {
        //arrange
        TaskEntity entity = new TaskEntity();
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
    void givenTaskDataAccess_whenFindAll_thenShouldReturnTaskList() {
        //arrange
        Integer expectedSize = 1;
        TaskEntity entity = new TaskEntity();
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

}
