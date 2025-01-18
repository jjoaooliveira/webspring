package AnnotationUseCase;

import com.webapp.usecase.annotation.DeleteAnnotationUseCase;
import com.webapp.usecase.data_access.AnnotationRepository;
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
        String id = "134234134";
        deleteAnnotationUseCase.execute(id);

        verify(mockAnnotationRepository, times(1)).delete(id);
    }
}
