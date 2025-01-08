package AnnotationUseCase;

import com.webapp.entity.Annotation;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.annotation.ReadAllAnnotationUseCase;
import com.webapp.usecase.dataaccess.AnnotationRepository;
import com.webapp.usecase.dto.annotation.AnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReadAllAnnotationUseCaseTest {
    @Mock
    AnnotationRepository mockAnnotationRepository;

    @Mock
    AnnotationMapper mockAnnotationMapper;

    @Mock
    AnnotationDTO mockAnnotationDTO1;

    @Mock
    AnnotationDTO mockAnnotationDTO2;

    @Mock
    Annotation mockAnnotation1;

    @Mock
    Annotation mockAnnotation2;

    @Mock
    OutputAnnotationDTO mockOutputAnnotationDTO1;

    @Mock
    OutputAnnotationDTO mockOutputAnnotationDTO2;

    @InjectMocks
    ReadAllAnnotationUseCase readAllAnnotationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenExecute_thenReturnListOfAnnotationWithSizeEquals2() throws TextLengthOverLimitException, EmptyTextException {
        int expectSize = 2;
        List<AnnotationDTO> annotationDTOS = List.of(mockAnnotationDTO1, mockAnnotationDTO2);

        when(mockAnnotationRepository.findAll()).thenReturn(annotationDTOS);
        when(mockAnnotationMapper.toAnnotation(mockAnnotationDTO1)).thenReturn(mockAnnotation1);
        when(mockAnnotationMapper.toAnnotation(mockAnnotationDTO2)).thenReturn(mockAnnotation2);
        when(mockAnnotationMapper.toOutputDTO(mockAnnotation1)).thenReturn(mockOutputAnnotationDTO1);
        when(mockAnnotationMapper.toOutputDTO(mockAnnotation1)).thenReturn(mockOutputAnnotationDTO2);

        List<OutputAnnotationDTO> actualAnnotationOutputDTOS = readAllAnnotationUseCase.execute();

        assertEquals(expectSize, actualAnnotationOutputDTOS.size());
    }
}
