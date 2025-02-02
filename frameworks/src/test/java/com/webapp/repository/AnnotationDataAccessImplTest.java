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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@EnableAutoConfiguration
@ContextConfiguration(classes = {
        AnnotationRepository.class,
        AnnotationEntity.class,
        AnnotationMapper.class
})
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AnnotationDataAccessImplTest {
    @Autowired
    AnnotationRepository annotationRepository;

    @Autowired
    AnnotationMapper annotationMapper;

    AnnotationRepositoryAPI annotationRepositoryAPI;

    @BeforeEach
    void setUp() {
        annotationRepositoryAPI = new AnnotationRepositoryAPI(annotationRepository, annotationMapper);

    }

    @Test
    @DisplayName("When Save Annotation With Success Should Return Valid Annotation")
    void givenAnnotation_whenSave_thenShouldReturnAnnotation() {
        //arrange
        Annotation annotation = new Annotation(
            new Title("Title"),
            new Content("Content"),
            new TimeMark(null)
        );

        //act
        var actual = annotationRepositoryAPI.annotationDataAccessSave(annotation);

        //assert
        assertNotNull("The actual Annotation should not be null", actual);
        assertEquals("The actual Annotation Title is incorrect", annotation.getTitle(), actual.getTitle());
        assertEquals("The actual Annotation Content is incorrect", annotation.getContent(), actual.getContent());
        assertEquals("The actual Annotation Creation is incorrect", annotation.getCreation(), actual.getCreation());
    }

    @Test
    @DisplayName("When FindAll Method Success Should Return Annotation List")
    void givenAnnotationDataAccess_whenFindAll_thenShouldReturnAnnotationList() {
        //arrange
        Integer expectedSize = 1;
        AnnotationEntity entity1 = new AnnotationEntity();
        entity1.setTitle("Title 1");
        entity1.setContent("Content 1");
        entity1.setCreation(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
        annotationRepository.save(entity1);

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
        AnnotationEntity entity1 = new AnnotationEntity();
        entity1.setTitle("Title 1");
        entity1.setContent("Content 1");
        entity1.setCreation(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
        annotationRepository.save(entity1);
        UUID uuid = entity1.getUUID();

        //act
        annotationRepositoryAPI.annotationDataAccessDelete(uuid);
        var actual = annotationRepositoryAPI.annotationDataAccessFindAll();

        //assert
        assertEquals("Actual list size should be zero", 0, actual.size());
    }
}
