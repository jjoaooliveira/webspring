package com.webapp.repository;

import com.webapp.entity.*;
import com.webapp.repository.api.TaskRepositoryAPI;
import com.webapp.repository.entity.TaskEntity;
import com.webapp.repository.mapper.TaskPersistenceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TaskDataAccessImplTest {
    @Mock
    TaskRepository mockTaskRepository;

    @Mock
    TaskPersistenceMapper mockTaskPersistenceMapper;

    @Mock
    TaskEntity mockTestTaskEntity;

    @InjectMocks
    TaskRepositoryAPI taskRepositoryAPI;

    Task task;

    @BeforeEach
    void setUp() {
        task = new Task(
                UUID.randomUUID(),
                new Title("Title"),
                new Content("Content1"),
                new TimeMark(),
                new TimedMark(ZonedDateTime.now()),
                true
        );
    }

    @Test
    @DisplayName("When Save Task Should Return Task With Valid Fields")
    void givenTask_whenSave_thenReturnTask() {
        //arrange
        when(mockTaskPersistenceMapper.toPersistence(task)).thenReturn(mockTestTaskEntity);
        when(mockTaskRepository.save(mockTestTaskEntity)).thenReturn(mockTestTaskEntity);
        when(mockTaskPersistenceMapper.toTask(mockTestTaskEntity)).thenReturn(task);

        //act
        var actual = taskRepositoryAPI.taskDataAccessSave(task);

        //assert
        assertNotNull("The actual Task should not be null", actual);
        assertEquals("The actual Task UUID is incorrect", task.getId(), actual.getId());
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
        UUID id = UUID.randomUUID();

        //act
        taskRepositoryAPI.taskDataAccessDelete(id);

        //assert
        verify(mockTaskRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("When FindAll Method Should Return a List of Task")
    void givenTaskDataAccess_whenFindAll_thenShouldReturnTaskList() {
        //arrange
        Integer expectedSize = 1;
        List<TaskEntity> taskEntityList = List.of(mockTestTaskEntity);

        when(mockTaskRepository.findAll()).thenReturn(taskEntityList);
        when(mockTaskPersistenceMapper.toTask(mockTestTaskEntity)).thenReturn(task);

        //act
        var actual = taskRepositoryAPI.taskDataAccessFindAll();

        //assert
        assertNotNull("Actual list should not be null", actual);
        assertEquals("Actual list size should be one", expectedSize, actual.size());

    }

}
