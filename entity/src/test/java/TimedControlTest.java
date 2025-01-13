import com.webapp.entity.TimedControl;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimedControlTest {
    @Test
    void givenCreationExpirationWithZoneEqualsToRioBrancoStrings_whenGetExpiration_thenReturnExpirationWithZoneEqualsToSaoPauloString() {
        String expectExpiration = "2024-12-31T01:59-03:00[America/Sao_Paulo]";

        TimedControl timedControl = new TimedControl(
                "2024-12-25T15:40",
                "2024-12-30T23:59",
                "America/Rio_Branco"
        );

        var actualExpiration = timedControl.getExpiration();

        assertEquals(expectExpiration, actualExpiration);
    }

    @Test
    void givenCreationExpirationAndZoneStrings_whenGetCreation_thenReturnStringCreation() {
        String expectCreation = "2024-12-25T15:40-03:00[America/Sao_Paulo]";

        TimedControl timedControl = new TimedControl(
                "2024-12-25T15:40",
                "2024-12-30T23:59",
                "America/Sao_Paulo"
        );

        var actualCreation = timedControl.getCreation();

        assertEquals(expectCreation, actualCreation);
    }

    @Test
    void givenCreationExpirationAndInvalidZoneStrings_whenTimedControlConstructor_thenThrowsDataTimeException() {
        assertThrows(DateTimeException.class, () -> new TimedControl(
                "2024-12-25T15:40",
                "2024-12-30T23:59",
                "Lorem"
        ));
    }

    @Test
    void givenInvalidCreationExpirationAndZoneStrings_whenTimedControlConstructor_thenThrowsDataTimeException() {
        assertThrows(DateTimeException.class, () -> new TimedControl(
                "",
                "",
                "America/Sao_Paulo"
        ));
    }

}
