package AnnotationUseCase;

import com.webapp.usecase.annotation.DeleteAnnotationUseCase;
import com.webapp.usecase.dataaccess.AnnotationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteAnnotationUseCaseTest {
    @Mock
    AnnotationRepository mockAnnotationRepository;

    @InjectMocks
    DeleteAnnotationUseCase deleteAnnotationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenAnIdValue_whenExecute_thenCallRepositoryDeleteMethodOneTime() {
        long id = 1L;
        deleteAnnotationUseCase.execute(id);

        verify(mockAnnotationRepository, times(1)).delete(id);
    }
}
