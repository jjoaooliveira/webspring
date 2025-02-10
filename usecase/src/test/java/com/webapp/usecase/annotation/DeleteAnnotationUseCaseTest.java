package com.webapp.usecase.AnnotationUseCase;

import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteAnnotationUseCaseTest {
    @Mock
    AnnotationDataAccess mockAnnotationDataAccess;

    UseCaseAPI useCaseAPI;

    UUID id;

    @BeforeEach
    void setUp() {
        useCaseAPI = new UseCaseAPI();
        id = UUID.randomUUID();
    }

    @Test
    void givenAnIdValue_whenExecute_thenCallRepositoryDeleteMethodOneTime() {
        //arrange

        //act
        useCaseAPI.deleteAnnotation(id, mockAnnotationDataAccess);

        //assert
        verify(mockAnnotationDataAccess, times(1)).delete(id);
    }
}
