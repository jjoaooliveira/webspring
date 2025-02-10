package com.webapp.repository;

import com.webapp.entity.*;
import com.webapp.repository.api.AnnotationRepositoryAPI;
import com.webapp.repository.entity.AnnotationEntity;
import com.webapp.repository.mapper.AnnotationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

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
    @DisplayName("When Find All Annotation With Success Should Return Annotation List")
    void givenAnnotationEntity_whenFindAll_thenShouldReturnAnnotationList() {
        //arrange
        Integer expectedSize = 1;
        AnnotationEntity entity = new AnnotationEntity();
        entity.setTitle("Title 1");
        entity.setContent("Content 1");
        entity.setCreation(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
        annotationRepository.save(entity);

        //act
        var actual = annotationRepositoryAPI.annotationDataAccessFindAll();

        //assert
        assertNotNull("Actual list should not be null", actual);
        assertEquals("Actual list size should be one", expectedSize, actual.size());
    }

    @Test
    @DisplayName("When Delete Task With Success Should Delete Annotation")
    void givenAnnotationId_whenDelete_thenShouldDeleteAnnotation() {
        //arrange
        AnnotationEntity entity = new AnnotationEntity();
        entity.setTitle("Title 1");
        entity.setContent("Content 1");
        entity.setCreation(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
        UUID uuid = entity.getUUID();

        //act
        annotationRepositoryAPI.annotationDataAccessDelete(uuid);
        var actual = annotationRepositoryAPI.annotationDataAccessFindAll();

        //assert
        assertEquals("Actual list size should be zero", 0, actual.size());
    }

    @Test
    @DisplayName("When Find Annotation By Title With Success Should Return List Of Annotation")
    void givenTitle_whenFindByTitle_thenReturnListOfAnnotation() {
        //arrange
        String expectedTitle = "Simple Title";
        AnnotationEntity annotationEntity1 = new AnnotationEntity(
                UUID.randomUUID(),
                "Simple Title",
                "Content",
                OffsetDateTime.now()
        );

        AnnotationEntity annotationEntity2 = new AnnotationEntity(
                UUID.randomUUID(),
                "Simple Title",
                "Content",
                OffsetDateTime.now()
        );
        annotationRepository.save(annotationEntity1);
        annotationRepository.save(annotationEntity2);

        //act
        var actual = annotationRepositoryAPI.annotationDataAccessFindByTitle("Simple Title");

        //assert
        assertEquals("The Title is incorrect", expectedTitle, actual.getFirst().getTitle());
        assertEquals("The Title is incorrect", expectedTitle, actual.getLast().getTitle());
    }

    @Test
    @DisplayName("When Find Annotation By Id With Success Should Return Correct Annotation")
    void givenAnnotationId_whenFindById_thenShouldReturnAnnotation() {
        //arrange
        String expectedTitle = "Title";
        String expectedContent = "Content";
        OffsetDateTime expectedCreation = OffsetDateTime.parse("2025-01-01T00:00:00-03:00");

        AnnotationEntity annotationEntity = new AnnotationEntity();
        annotationEntity.setTitle("Title");
        annotationEntity.setContent("Content");
        annotationEntity.setCreation(OffsetDateTime.parse("2025-01-01T00:00:00-03:00"));
        annotationEntity = annotationRepository.save(annotationEntity);
        UUID uuid = annotationEntity.getUUID();

        //act
        var actual = annotationRepositoryAPI.annotationDataAccessFindById(uuid);

        //assert
        assertNotNull("The actual Entity should not be null", actual);
        assertEquals("The Id is incorrect", uuid, actual.getId());
        assertEquals("The Title is incorrect", expectedTitle, actual.getTitle());
        assertEquals("The Content is incorrect", expectedContent, actual.getContent());
        assertEquals("The Creation is incorrect", expectedCreation, actual.getCreation());
    }
}
