package TaskUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.webapp.entity.Content;
import com.webapp.entity.Task;
import com.webapp.entity.Title;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dataaccess.TaskRepository;
import com.webapp.usecase.dto.task.DatabaseTaskDTO;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.DatabaseMapper;
import com.webapp.usecase.mapper.TaskMapper;
import com.webapp.usecase.task.SaveTaskUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class SaveTaskUseCaseTest {
    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;

    @Mock
    DatabaseMapper databaseMapper;

    @InjectMocks
    SaveTaskUseCase saveTaskUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSavedTaskDataFromDataBase() throws TextLengthOverLimitException, EmptyTextException {
        LocalDateTime expirationLocalDateTime = LocalDateTime.now();
        
        InputTaskDTO taskDTO = new InputTaskDTO(
                Optional.empty(),
                Optional.of("Teste"),
                Optional.of("Conteudo 1"),
                Optional.of(expirationLocalDateTime.toString())
        );

        Task task = new Task(
                new Title(taskDTO.title().get()),
                Content.create(taskDTO.content().get()),
                LocalDateTime.parse(taskDTO.expirationDate().get())
        );

        DatabaseTaskDTO inputDatabaseTaskDTO = new DatabaseTaskDTO(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getCreationDate().toString(),
                task.getExpirationDate().toString(),
                task.isCompleted()
        );

        DatabaseTaskDTO outputDatabaseTaskDTO = new DatabaseTaskDTO(
                1L,
                task.getTitle(),
                task.getContent(),
                task.getCreationDate().toString(),
                task.getExpirationDate().toString(),
                task.isCompleted()
        );

        Task responseTask = new Task(
                outputDatabaseTaskDTO.id(),
                new Title(outputDatabaseTaskDTO.title()),
                Content.create(outputDatabaseTaskDTO.content()),
                outputDatabaseTaskDTO.creationDate(),
                outputDatabaseTaskDTO.expirationDate(),
                outputDatabaseTaskDTO.completed()
        );

        OutputTaskDTO outputTaskDTO = new OutputTaskDTO(
                responseTask.getId(),
                responseTask.getTitle(),
                responseTask.getContent(),
                responseTask.getCreationDate().toLocalDate().toString(),
                responseTask.getCreationDate().toLocalTime().toString(),
                responseTask.getExpirationDate().toLocalDate().toString(),
                responseTask.getExpirationDate().toLocalTime().toString(),
                responseTask.getTimeLeft(),
                responseTask.isCompleted(),
                responseTask.isExpired()
        );

        when(taskMapper.toEntity(taskDTO)).thenReturn(task);
        when(databaseMapper.toDTO(task)).thenReturn(inputDatabaseTaskDTO);
        when(taskRepository.save(inputDatabaseTaskDTO)).thenReturn(outputDatabaseTaskDTO);
        when(databaseMapper.toEntity(outputDatabaseTaskDTO)).thenReturn(responseTask);
        when(taskMapper.toDTO(responseTask)).thenReturn(outputTaskDTO);

        OutputTaskDTO result = saveTaskUseCase.execute(taskDTO);

        assertEquals(task.getContent(), result.content());
        assertEquals(task.getCreationDate().toLocalDate().toString(), outputTaskDTO.creationDate());
        assertEquals(task.getCreationDate().toLocalTime().toString(), outputTaskDTO.creationTime());
        assertEquals(task.getExpirationDate().toLocalDate().toString(), outputTaskDTO.expirationDate());
        assertEquals(task.getExpirationDate().toLocalTime().toString(), outputTaskDTO.expirationTime());
        assertEquals(responseTask.getId(), outputTaskDTO.id());
    }
}
