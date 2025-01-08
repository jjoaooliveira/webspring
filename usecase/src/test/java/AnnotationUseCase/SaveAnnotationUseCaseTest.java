package AnnotationUseCase;

import com.webapp.entity.Annotation;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.annotation.SaveAnnotationUseCase;
import com.webapp.usecase.dataaccess.AnnotationRepository;
import com.webapp.usecase.dto.annotation.AnnotationDTO;
import com.webapp.usecase.dto.annotation.NewAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class SaveAnnotationUseCaseTest {

    @Mock
    AnnotationRepository mockAnnotationRepository;

    @Mock
    AnnotationMapper mockAnnotationMapper;

    @Mock
    Annotation mockAnnotation;

    @Mock
    Annotation mockReturnedDatabaseAnnotation;

    @Mock
    NewAnnotationDTO mockNewAnnotationDTO;

    @Mock
    AnnotationDTO mockAnnotationDTO;

    @Mock
    AnnotationDTO mockReturnedDatabaseAnnotationDTO;

    @Mock
    OutputAnnotationDTO mockActualOutputAnnotationDTO;

    @InjectMocks
    SaveAnnotationUseCase saveAnnotationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void whenExecute_thenReturnNotNullOutputAnnotationDTO() throws TextLengthOverLimitException, EmptyTextException {

        when(mockAnnotationMapper.toAnnotation(mockNewAnnotationDTO)).thenReturn(mockAnnotation);
        when(mockAnnotationMapper.toAnnotationDTO(mockAnnotation)).thenReturn(mockAnnotationDTO);
        when(mockAnnotationRepository.save(mockAnnotationDTO)).thenReturn(mockReturnedDatabaseAnnotationDTO);
        when(mockAnnotationMapper.toAnnotation(mockReturnedDatabaseAnnotationDTO)).thenReturn(mockReturnedDatabaseAnnotation);
        when(mockAnnotationMapper.toOutputDTO(mockReturnedDatabaseAnnotation)).thenReturn(mockActualOutputAnnotationDTO);

        OutputAnnotationDTO actualOutputAnnotationDTO = saveAnnotationUseCase.execute(mockNewAnnotationDTO);
        assertNotNull(actualOutputAnnotationDTO);
    }
}
