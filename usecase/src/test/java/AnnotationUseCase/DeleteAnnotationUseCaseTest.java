package AnnotationUseCase;

import com.webapp.usecase.annotation.DeleteAnnotationUseCase;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteAnnotationUseCaseTest {
    @Mock
    AnnotationDataAccess mockAnnotationDataAccess;

    UUID id;

    @InjectMocks
    DeleteAnnotationUseCase deleteAnnotationUseCase;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenAnIdValue_whenExecute_thenCallRepositoryDeleteMethodOneTime() {
        deleteAnnotationUseCase.execute(id);

        verify(mockAnnotationDataAccess, times(1)).delete(id);
    }
}
