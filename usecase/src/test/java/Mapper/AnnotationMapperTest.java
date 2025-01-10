package Mapper;

import com.webapp.entity.Annotation;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dto.annotation.AnnotationDTO;
import com.webapp.usecase.dto.annotation.NewAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class AnnotationMapperTest {
    @Mock
    Annotation mockAnnotation;

    @Mock
    AnnotationDTO mockAnnotationDTO;

    @Mock
    NewAnnotationDTO mockNewAnnotationDTO;

    @Mock
    LocalDateTime mockLocalDateTime;

    @Mock
    LocalDate mockLocalDate;

    @Mock
    LocalTime mockLocalTime;

    @InjectMocks
    AnnotationMapper annotationMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenAnnotation_whenToOutputDTO_thenReturnNotNullOutputAnnotationDTO() {
        Long expectId = 1L;
        String expectTitle = "mockTitle";
        String expectContent = "mockContent";
        String expectDate = "2024-12-25";
        String expectTime = "15:00";

        when(mockAnnotation.getId()).thenReturn(1L);
        when(mockAnnotation.getTitle()).thenReturn("mockTitle");
        when(mockAnnotation.getContent()).thenReturn("mockContent");
        when(mockAnnotation.getCreationDate()).thenReturn(mockLocalDateTime);
        when(mockLocalDateTime.toLocalDate()).thenReturn(mockLocalDate);
        when(mockLocalDateTime.toLocalTime()).thenReturn(mockLocalTime);
        when(mockLocalDate.toString()).thenReturn("2024-12-25");
        when(mockLocalTime.toString()).thenReturn("15:00");

        OutputAnnotationDTO actualOutputAnnotationDTO = annotationMapper.toOutputDTO(mockAnnotation);

        assertNotNull(actualOutputAnnotationDTO);
        assertEquals(expectId, actualOutputAnnotationDTO.id());
        assertEquals(expectTitle, actualOutputAnnotationDTO.title());
        assertEquals(expectContent, actualOutputAnnotationDTO.content());
        assertEquals(expectDate, actualOutputAnnotationDTO.creationDate());
        assertEquals(expectTime, actualOutputAnnotationDTO.creationTime());

    }

    @Test
    void givenAnnotationDTO_whenToAnnotation_thenReturnNotNullAnnotation() throws TextLengthOverLimitException, EmptyTextException {
        String expectTitle = "mockTitle";
        String expectContent = "mockContent";
        String expectCreationDate = "2024-12-25T15:00";

        when(mockAnnotationDTO.title()).thenReturn("mockTitle");
        when(mockAnnotationDTO.content()).thenReturn("mockContent");
        when(mockAnnotationDTO.creationDate()).thenReturn("2024-12-25T15:00");

        var actualAnnotation = annotationMapper.toAnnotation(mockAnnotationDTO);

        assertNotNull(actualAnnotation);
        assertEquals(expectTitle, actualAnnotation.getTitle());
        assertEquals(expectContent, actualAnnotation.getContent());
        assertEquals(expectCreationDate, actualAnnotation.getCreationDate().toString());
    }

    @Test
    void givenAnnotation_whenToAnnotationDTO_thenReturnNotNullAnnotationDTO() {
        String expectTitle = "mockTitle";
        String expectContent = "mockContent";
        String expectCreationDate = "2024-12-25T15:00-03:00 [America/Sao_paulo]";

        when(mockAnnotation.getId()).thenReturn(1L);
        when(mockAnnotation.getTitle()).thenReturn("mockTitle");
        when(mockAnnotation.getContent()).thenReturn("mockContent");
        when(mockAnnotation.getCreationDate()).thenReturn(mockLocalDateTime);
        when(mockLocalDateTime.toString()).thenReturn("2024-12-25T15:00-03:00 [America/Sao_paulo]");

        var actualAnnotation = annotationMapper.toAnnotationDTO(mockAnnotation);

        assertNotNull(actualAnnotation);
        assertEquals(expectTitle, actualAnnotation.title());
        assertEquals(expectContent, actualAnnotation.content());
        assertEquals(expectCreationDate, actualAnnotation.creationDate());
    }

    @Test
    void givenNewAnnotationDTO_whenToAnnotation_thenReturnNotNullAnnotation() throws TextLengthOverLimitException, EmptyTextException {
        String expectTitle = "mockTitle";
        String expectContent = "mockContent";

        when(mockNewAnnotationDTO.title()).thenReturn("mockTitle");
        when(mockNewAnnotationDTO.content()).thenReturn("mockContent");

        var actualAnnotation = annotationMapper.toAnnotation(mockNewAnnotationDTO);

        assertNotNull(actualAnnotation);
        assertEquals(expectTitle, actualAnnotation.getTitle());
        assertEquals(expectContent, actualAnnotation.getContent());
    }
}
