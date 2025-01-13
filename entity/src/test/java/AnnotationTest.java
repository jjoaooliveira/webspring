import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.webapp.entity.TimeControl;
import com.webapp.entity.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AnnotationTest {
    @Mock
    Title mockTitle;

    @Mock
    Content mockContent;

    @Mock
    TimeControl mockTimeControl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void givenAnnotation_whenToString_thenReturnAnnotationString() {
        String expectAnnotationString = "Teste";

        when(mockTitle.getText()).thenReturn("Teste");

        Annotation annotation = new Annotation(mockTitle, mockContent, mockTimeControl);
        var actualAnnotationString = annotation.toString();

        assertEquals(expectAnnotationString, actualAnnotationString);
    }
}
