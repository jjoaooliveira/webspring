import com.webapp.entity.TimedMark;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimedMarkTest {
    @Test
    void givenRioBrancoExpirationAndZoneStrings_whenGetExpiration_thenReturnExpirationWithSaoPauloZoneString() {
        String expectExpiration = "2024-12-31T01:59-03:00[America/Sao_Paulo]";

        TimedMark timedControl = new TimedMark(
                "2024-12-30T23:59",
                "America/Rio_Branco"
        );

        var actualExpiration = timedControl.getExpiration();

        assertEquals(expectExpiration, actualExpiration);
    }

    @Test
    void givenCreationExpirationAndInvalidZoneStrings_whenCreatingTimedControl_thenThrowsDataTimeException() {
        assertThrows(DateTimeException.class, () -> new TimedMark(
                "2024-12-30T23:59",
                "Lorem"
        ));
    }

    @Test
    void givenInvalidCreationExpirationAndZoneStrings_whenCreatingTimedControl_thenThrowsDataTimeException() {
        assertThrows(DateTimeException.class, () -> new TimedMark(
                "",
                "America/Sao_Paulo"
        ));
    }

}
