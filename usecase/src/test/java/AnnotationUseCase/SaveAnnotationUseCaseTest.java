package AnnotationUseCase;

import com.webapp.entity.Annotation;
import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;
import com.webapp.usecase.annotation.SaveAnnotationUseCase;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import com.webapp.usecase.exception.FailToCreateAnnotationException;
import com.webapp.usecase.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class SaveAnnotationUseCaseTest {

    @Mock
    AnnotationDataAccess mockAnnotationDataAccess;

    @Mock
    AnnotationMapper mockAnnotationMapper;

    @Mock
    Annotation mockAnnotation;

    @Mock
    Annotation mockReturnedDatabaseAnnotation;

    @Mock
    InputAnnotationDTO mockInputAnnotationDTO;

    @Mock
    OutputAnnotationDTO mockOutputAnnotationDTO;

    @InjectMocks
    SaveAnnotationUseCase saveAnnotationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void givenAnnotationUseCase_whenExecute_thenReturnNotNullOutputAnnotationDTO() throws TextLengthOverLimitException, EmptyTextException {

        when(mockAnnotationMapper.toAnnotation(mockInputAnnotationDTO)).thenReturn(mockAnnotation);
        when(mockAnnotationDataAccess.save(mockAnnotation)).thenReturn(mockReturnedDatabaseAnnotation);
        when(mockAnnotationMapper.toOutputDTO(mockReturnedDatabaseAnnotation)).thenReturn(mockOutputAnnotationDTO);

        OutputAnnotationDTO actualOutputAnnotationDTO = saveAnnotationUseCase.execute(mockInputAnnotationDTO);
        assertNotNull(actualOutputAnnotationDTO);
    }

    @Test
    void givenAnnotationUseCase_whenThrowTextLengthOverLimitException_thenThrowFailToCreateAnnotationException() throws TextLengthOverLimitException, EmptyTextException {

        when(mockAnnotationMapper.toAnnotation(mockInputAnnotationDTO)).thenThrow(TextLengthOverLimitException.class);

        assertThrows(FailToCreateAnnotationException.class, () -> saveAnnotationUseCase.execute(mockInputAnnotationDTO));
    }

    @Test
    void givenAnnotationUseCase_whenThrowEmptyTextException_thenThrowFailToCreateAnnotationException() throws TextLengthOverLimitException, EmptyTextException {

        when(mockAnnotationMapper.toAnnotation(mockInputAnnotationDTO)).thenThrow(EmptyTextException.class);

        assertThrows(FailToCreateAnnotationException.class, () -> saveAnnotationUseCase.execute(mockInputAnnotationDTO));
    }
}
