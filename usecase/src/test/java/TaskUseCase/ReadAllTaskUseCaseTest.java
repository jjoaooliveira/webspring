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
import com.webapp.usecase.dto.task.OutputTaskDTO;
import com.webapp.usecase.mapper.DatabaseMapper;
import com.webapp.usecase.mapper.TaskMapper;
import com.webapp.usecase.task.ReadAllTaskReturnUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.ZonedDateTime;
import java.util.List;

public class ReadAllTaskUseCaseTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;
    @Mock
    DatabaseMapper databaseMapper;

    @InjectMocks
    ReadAllTaskReturnUseCase readAllTaskUseCase;

    @Test
    void shouldReturnOutputTaskDTOList() throws TextLengthOverLimitException, EmptyTextException {
        ZonedDateTime expirationZonedDateTime = ZonedDateTime.now();
        String stringExpirationDate = expirationZonedDateTime.toString();
        String stringExpirationLocalDate = expirationZonedDateTime.toLocalDate().toString();
        String stringExpirationLocalTime = expirationZonedDateTime.toLocalTime().toString();

        ZonedDateTime creationZonedDateTime1 = ZonedDateTime.now().minusHours(1);
        ZonedDateTime creationZonedDateTime2 = ZonedDateTime.now().minusHours(2);
        String stringCreationDate1 = creationZonedDateTime1.toString();
        String stringCreationDate2 = creationZonedDateTime2.toString();

        String stringCreationLocalDate1 = creationZonedDateTime1.toLocalDate().toString();
        String stringCreationLocalDate2 = creationZonedDateTime2.toLocalDate().toString();

        String stringCreationLocalTime1 = creationZonedDateTime1.toLocalTime().toString();
        String stringCreationLocalTime2 = creationZonedDateTime2.toLocalTime().toString();


        Task task1 = new Task(
                1L,
                new Title("Teste"),
                Content.create("Teste"),
                stringCreationDate1,
                stringExpirationDate,
                true
        );

        Task task2 = new Task(
                2L,
                new Title("Teste"),
                Content.create("Teste"),
                stringCreationDate2,
                stringExpirationDate,
                true
        );

        DatabaseTaskDTO databaseTaskDTO1 = new DatabaseTaskDTO(
                1L,
                "Teste",
                "Conteudo",
                stringCreationDate1,
                stringExpirationDate,
                true
        );

        DatabaseTaskDTO databaseTaskDTO2 = new DatabaseTaskDTO(
                2L,
                "Teste",
                "Conteudo",
                stringCreationDate2,
                stringExpirationDate,
                false
        );

        List<DatabaseTaskDTO> dbTaskList = List.of(databaseTaskDTO1, databaseTaskDTO2);

        OutputTaskDTO outputTaskDTO1 = new OutputTaskDTO(
                task1.getId(),
                task1.getTitle(),
                task1.getContent(),
                stringCreationLocalDate1,
                stringCreationLocalTime1,
                stringExpirationDate,
                stringExpirationLocalTime,
                task1.getTimeLeft(),
                task1.isCompleted(),
                task1.isExpired()
        );

        OutputTaskDTO outputTaskDTO2 = new OutputTaskDTO(
                task2.getId(),
                task2.getTitle(),
                task2.getContent(),
                stringCreationLocalDate2,
                stringCreationLocalTime2,
                stringExpirationLocalDate,
                stringExpirationLocalTime,
                task2.getTimeLeft(),
                task2.isCompleted(),
                task2.isExpired()
        );

        when(taskRepository.findAll()).thenReturn(dbTaskList);
        when(databaseMapper.toEntity(databaseTaskDTO1)).thenReturn(task1);
        when(databaseMapper.toEntity(databaseTaskDTO2)).thenReturn(task2);
        when(taskMapper.toDTO(task1)).thenReturn(outputTaskDTO1);
        when(taskMapper.toDTO(task2)).thenReturn(outputTaskDTO2);

        List<OutputTaskDTO> returnedTaskDTOList = readAllTaskUseCase.execute();

        assertEquals(task1.getId(), returnedTaskDTOList.get(1).id());
        assertEquals(task1.getTitle(), returnedTaskDTOList.get(0).title());
        assertEquals(task1.getContent(), returnedTaskDTOList.get(0).content());
        assertEquals(task1.getCreationDate().toLocalDate().toString(), returnedTaskDTOList.get(0).creationDate());
        assertEquals(task1.getCreationDate().toLocalTime().toString(), returnedTaskDTOList.get(0).creationTime());
        assertEquals(task1.getExpirationDate().toLocalDate().toString(), returnedTaskDTOList.get(0).expirationDate());
        assertEquals(task1.getExpirationDate().toLocalTime().toString(), returnedTaskDTOList.get(0).expirationTime());

        assertEquals(task2.getId(), returnedTaskDTOList.get(1).id());
        assertEquals(task2.getTitle(), returnedTaskDTOList.get(1).title());
        assertEquals(task2.getContent(), returnedTaskDTOList.get(1).content());
        assertEquals(task2.getCreationDate().toLocalDate().toString(), returnedTaskDTOList.get(1).creationDate());
        assertEquals(task2.getCreationDate().toLocalTime().toString(), returnedTaskDTOList.get(1).creationTime());
        assertEquals(task2.getExpirationDate().toLocalDate().toString(), returnedTaskDTOList.get(1).expirationDate());
        assertEquals(task2.getExpirationDate().toLocalTime().toString(), returnedTaskDTOList.get(1).expirationTime());
        assertEquals(task2.isCompleted(), returnedTaskDTOList.get(1).completed());

    }
}
