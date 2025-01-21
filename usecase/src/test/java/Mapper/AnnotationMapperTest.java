package Mapper;

import com.webapp.entity.Annotation;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
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
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AnnotationMapperTest {
    @Mock
    Annotation mockAnnotation;

    @Mock
    InputAnnotationDTO mockInputAnnotationDTO;

    @InjectMocks
    AnnotationMapper annotationMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenAnnotation_whenToOutputDTO_thenReturnNotNullOutputAnnotationDTO() {
        UUID expectId = UUID.randomUUID();
        String expectTitle = "mockTitle";
        String expectContent = "mockContent";
        String expectDate = "2024-12-25T15:00-03:00";
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2024-12-25T15:00-03:00");

        when(mockAnnotation.getId()).thenReturn(expectId);
        when(mockAnnotation.getTitle()).thenReturn("mockTitle");
        when(mockAnnotation.getContent()).thenReturn("mockContent");
        when(mockAnnotation.getCreation()).thenReturn(offsetDateTime);

        OutputAnnotationDTO actualOutputAnnotationDTO = annotationMapper.toOutputDTO(mockAnnotation);

        assertNotNull(actualOutputAnnotationDTO);
        assertEquals(expectId, actualOutputAnnotationDTO.id());
        assertEquals(expectTitle, actualOutputAnnotationDTO.title());
        assertEquals(expectContent, actualOutputAnnotationDTO.content());
        assertEquals(expectDate, actualOutputAnnotationDTO.creation().toString());

    }

    @Test
    void givenInputAnnotationDTO_whenToAnnotation_thenReturnNotNullAnnotation() {
        String expectTitle = "mockTitle";
        String expectContent = "mockContent";

        when(mockInputAnnotationDTO.title()).thenReturn("mockTitle");
        when(mockInputAnnotationDTO.content()).thenReturn("mockContent");

        var actualAnnotation = annotationMapper.toAnnotation(mockInputAnnotationDTO);

        assertNotNull(actualAnnotation);
        assertEquals(expectTitle, actualAnnotation.getTitle());
        assertEquals(expectContent, actualAnnotation.getContent());
    }
}
