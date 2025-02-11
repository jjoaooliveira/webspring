package com.webapp.usecase.annotation;

import com.webapp.usecase.api.UseCaseAPI;
import com.webapp.usecase.data_access.AnnotationDataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @BeforeEach
    void setUp() {
        useCaseAPI = new UseCaseAPI();
    }

    @Test
    @DisplayName("When Execute With Success Should Call Delete Method")
    void givenIdValue_whenExecute_thenCallDeleteMethodOneTime() {
        //arrange
        UUID id = UUID.randomUUID();

        //act
        useCaseAPI.deleteAnnotation(id, mockAnnotationDataAccess);

        //assert
        verify(mockAnnotationDataAccess, times(1)
                .description("Should call delete method only one time"))
                .delete(id);
    }
}
