package com.webapp.repository;

import com.webapp.entity.*;
import com.webapp.repository.api.AnnotationRepositoryAPI;
import com.webapp.repository.entity.AnnotationEntity;
import com.webapp.repository.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AnnotationDataAccessImplTest {
    @Mock
    AnnotationRepository mockAnnotationRepository;

    @Mock
    AnnotationMapper mockAnnotationMapper;

    @Mock
    AnnotationEntity mockAnnotationEntity;

    @InjectMocks
    AnnotationRepositoryAPI annotationRepositoryAPI;

    Annotation annotation;

    @BeforeEach
    void setUp() {
        annotation = new Annotation(
            UUID.randomUUID(),
            new Title("Title"),
            new Content("Content"),
            new TimeMark()
        );
    }

    @Test
    @DisplayName("When Save Annotation With Success Should Return Valid Annotation")
    void givenAnnotation_whenSave_thenShouldReturnAnnotation() {
        //arrange
        when(mockAnnotationMapper.toEntity(annotation)).thenReturn(mockAnnotationEntity);
        when(mockAnnotationRepository.save(mockAnnotationEntity)).thenReturn(mockAnnotationEntity);
        when(mockAnnotationMapper.toAnnotation(mockAnnotationEntity)).thenReturn(annotation);

        //act
        var actual = annotationRepositoryAPI.annotationDataAccessSave(annotation);

        //assert
        assertNotNull("The actual Annotation should not be null", actual);
        assertEquals("The actual Annotation UUID is incorrect", annotation.getId(), actual.getId());
        assertEquals("The actual Annotation Title is incorrect", annotation.getTitle(), actual.getTitle());
        assertEquals("The actual Annotation Content is incorrect", annotation.getContent(), actual.getContent());
        assertEquals("The actual Annotation Creation is incorrect", annotation.getCreation(), actual.getCreation());
    }

    @Test
    @DisplayName("When FindAll Method Success Should Return Annotation List")
    void givenAnnotationDataAccess_whenFindAll_thenShouldReturnAnnotationList() {
        //arrange
        Integer expectedSize = 1;
        List<AnnotationEntity> annotationEntityList = List.of(mockAnnotationEntity);

        when(mockAnnotationRepository.findAll()).thenReturn(annotationEntityList);
        when(mockAnnotationMapper.toAnnotation(mockAnnotationEntity)).thenReturn(annotation);

        //act
        var actual = annotationRepositoryAPI.annotationDataAccessFindAll();

        //assert
        assertNotNull("Actual list should not be null", actual);
        assertEquals("Actual list size should be one", expectedSize, actual.size());
    }

    @Test
    @DisplayName("When Delete Task Should Call Delete Method One Time")
    void givenAnnotationDataAccess_whenDelete_thenShouldCallRepositoryDelete() {
        //arrange
        UUID id = UUID.randomUUID();

        //act
        annotationRepositoryAPI.annotationDataAccessDelete(id);

        //assert
        verify(mockAnnotationRepository, times(1)).deleteById(id);
    }
}
