package com.webapp.repository.annotation;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.*;

@EnableAutoConfiguration
@ContextConfiguration(classes = {
        AnnotationRepository.class,
        EntityAnnotationMapper.class
})
@ExtendWith(SpringExtension.class)
@DataJpaTest
class AnnotationDataGatewayImplTest {
    @Autowired
    AnnotationRepository annotationRepository;

    @Autowired
    EntityAnnotationMapper entityAnnotationMapper;

    AnnotationDataGatewayImpl annotationDataGateway;

    @BeforeEach
    void setUp() {
        annotationDataGateway = new AnnotationDataGatewayImpl(annotationRepository, entityAnnotationMapper);
    }

    @Test
    @DisplayName("Save Annotation Should Return Saved Annotation With Correct Fields")
    void givenAnnotation_whenSave_thenShouldReturnAnnotation() {
        //arrange
        Annotation annotation = new Annotation(
                new Title("Title"),
                new Content("Content")
        );

        //act
        var actual = annotationDataGateway.save(annotation);

        //assert
        assertNotNull("The actual Annotation should not be null", actual);
        assertEquals("The actual Annotation title is incorrect", annotation.getTitle(), actual.getTitle());
        assertEquals("The actual Annotation content is incorrect", annotation.getContent(), actual.getContent());
        assertNotNull("The actual Annotation creation should not be null", actual.getCreation());
    }

    @Test
    @DisplayName("Find All Annotation Should Return Annotation List")
    void givenAnnotationEntity_whenFindAll_thenShouldReturnAnnotationList() {
        //arrange
        Integer expectedSize = 2;
        Annotation annotation1 = new Annotation(
                new Title("Title 1"),
                new Content("Content 1")
        );

        Annotation annotation2 = new Annotation(
                new Title("Title 2"),
                new Content("Content 2")
        );
        annotationDataGateway.save(annotation1);
        annotationDataGateway.save(annotation2);

        //act
        var actual = annotationDataGateway.findAll();

        //assert
        assertNotNull("Actual list should not be null", actual);
        assertEquals("Actual list size should be one", expectedSize, actual.size());
    }

    @Test
    @DisplayName("Should Return False To ExistsById After Delete Operation")
    void givenAnnotationId_whenDelete_thenShouldDeleteAnnotation() {
        //arrange
        Annotation annotation = new Annotation(
                new Title("Title"),
                new Content("Content")
        );
        Annotation savedAnnotation = annotationDataGateway.save(annotation);
        UUID uuid = savedAnnotation.getId();

        //act
        annotationDataGateway.delete(uuid);
        var actual = annotationRepository.existsById(uuid);

        //assert
        assertFalse("Actual should be false", actual);
    }

    @Test
    @DisplayName("Find Annotation By Title Should Return List Of Annotation")
    void givenTitle_whenFindByTitle_thenReturnListOfAnnotation() {
        //arrange
        String expectedTitle = "Simple Title";
        Annotation annotation1 = new Annotation(
                new Title("Simple Title"),
                new Content("Content 1")
        );

        Annotation annotation2 = new Annotation(
                new Title("Simple Title"),
                new Content("Content 2")
        );
        annotationDataGateway.save(annotation1);
        annotationDataGateway.save(annotation2);

        //act
        var actual = annotationDataGateway.findByTitle("Simple Title");

        //assert
        assertEquals("The actual annotation title is incorrect", expectedTitle, actual.getFirst().getTitle());
        assertEquals("The actual annotation title is incorrect", expectedTitle, actual.getLast().getTitle());
    }

    @Test
    @DisplayName("Find Annotation By Id Should Return Correct Annotation")
    void givenAnnotationId_whenFindById_thenShouldReturnAnnotation() {
        //arrange
        Annotation annotation = new Annotation(
                new Title("Title"),
                new Content("Content")
        );
        Annotation savedAnnotation = annotationDataGateway.save(annotation);
        UUID expectedId = savedAnnotation.getId();
        String expectedTitle = savedAnnotation.getTitle();
        String expectedContent = savedAnnotation.getContent();
        Instant expectedCreation = savedAnnotation.getCreation();

        //act
        var actual = annotationDataGateway.findById(expectedId);

        //assert
        assertNotNull("The actual annotation should not be null", actual);
        assertEquals("The actual annotation id is incorrect", expectedId, actual.getId());
        assertEquals("The actual annotation title is incorrect", expectedTitle, actual.getTitle());
        assertEquals("The actual annotation Content is incorrect", expectedContent, actual.getContent());
        assertEquals("The actual annotation creation is incorrect", expectedCreation, actual.getCreation());
    }
}
