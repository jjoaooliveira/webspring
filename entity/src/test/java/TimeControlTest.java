import com.webapp.entity.TimeControl;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

public class TimeControlTest {
    @Test
    void givenStringCreation_whenGetCreation_thenReturnStringCreation() {
        String expectCreation = "2024-12-25T15:30-03:00[America/Sao_Paulo]";

        TimeControl timeControl = new TimeControl("2024-12-25T15:30-03:00[America/Sao_Paulo]");
        String actualCreation = timeControl.getCreation();

        assertEquals(expectCreation, actualCreation);
    }

    @Test
    void givenStringDateTimeWithInvalidZone_whenTimeControlConstructor_thenThrownDateTimeException() {
        assertThrows(DateTimeException.class, () -> new TimeControl("Lorem"));
    }

    @Test
    void givenNoArgs_whenTimeControlConstructor_thenShouldCreateTimeControl() {
        TimeControl actualTimeControl = new TimeControl();

        assertNotNull(actualTimeControl);
    }
}
