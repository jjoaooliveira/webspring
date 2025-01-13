import com.webapp.entity.TimeControl;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeControlTest {
    @Test
    void givenStringDateTimeAndZoneOfSaoPaulo_whenGetCreation_thenReturnStringCreation() {
        String expectCreation = "2024-12-25T15:30-03:00[America/Sao_Paulo]";

        TimeControl timeControl = new TimeControl("2024-12-25T15:30", "America/Sao_Paulo");
        String actualCreation = timeControl.getCreation();

        assertEquals(expectCreation, actualCreation);
    }

    @Test
    void givenStringDateTimeAndZoneOfRioBranco_whenGetCreation_thenReturnConvertedStringCreationOfSaoPauloZone() {
        String expectCreation = "2024-12-25T17:30-03:00[America/Sao_Paulo]";

        TimeControl timeControl = new TimeControl("2024-12-25T15:30", "America/Rio_Branco");
        String actualCreation = timeControl.getCreation();

        assertEquals(expectCreation, actualCreation);
    }

    @Test
    void givenStringDateTimeWithInvalidZone_whenTimeControlConstructor_thenThrownDateTimeException() {
        assertThrows(DateTimeException.class, () -> new TimeControl("2024-12-25T15:30", ""));
    }

    @Test
    void givenStringZonedDateTime_whenGetCreation_thenThrownDateTimeException() {
        String expectCreation = "2024-12-25T15:30-03:00[America/Sao_Paulo]";

        TimeControl timeControl = new TimeControl("2024-12-25T15:30-03:00[America/Sao_Paulo]");
        String actualCreation = timeControl.getCreation();

        assertEquals(expectCreation, actualCreation);
    }
}
