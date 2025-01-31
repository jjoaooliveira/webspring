import com.webapp.entity.TimedMark;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimedMarkTest {

    @Test
    void givenRioBrancoZonedDateTime_whenGetExpiration_thenReturnExpirationWithSaoPauloZone() {
        String expectExpiration = "2024-12-31T01:59-03:00";

        TimedMark timedControl = new TimedMark(
            ZonedDateTime.parse("2024-12-30T23:59-05:00[America/Rio_Branco]")
        );

        var actualExpiration = timedControl.getExpiration();

        assertEquals(expectExpiration, actualExpiration.toString());
    }

    @Test
    void givenZonedDateTime_whenIsExpired_thenReturnTrue() {
        Boolean expectExpired = true;

        Clock clock = Clock.fixed(
                Instant.parse("2024-12-31T05:59:00Z"),
                ZoneOffset.UTC
        );

        TimedMark timedControl = new TimedMark(
            ZonedDateTime.parse("2024-12-30T23:59-05:00[America/Rio_Branco]")
        );

        var actualExpired = timedControl.isExpired(clock);

        assertEquals(expectExpired, actualExpired);
    }

    @Test
    void givenZonedDateTime_whenGetTimeLeft_thenReturn5DaysTimeLeft() {
        String expectTimeLeft = "5 d";

        Clock clock = Clock.fixed(
                Instant.parse("2024-12-26T02:59:00Z"),
                ZoneOffset.UTC
        );

        TimedMark timedControl = new TimedMark(
            ZonedDateTime.parse("2024-12-30T23:59-03:00[America/Sao_Paulo]")
        );

        var actualTimeLeft = timedControl.getTimeLeft(clock);

        assertEquals(expectTimeLeft, actualTimeLeft);
    }

    @Test
    void givenZonedDateTime_whenGetTimeLeft_thenReturn3HoursTimeLeft() {
        String expectTimeLeft = "3 min";

        Clock clock = Clock.fixed(
                Instant.parse("2024-12-26T02:59:00Z"),
                ZoneOffset.UTC
        );

        TimedMark timedControl = new TimedMark(
            ZonedDateTime.parse("2024-12-26T00:02-03:00[America/Sao_Paulo]")
        );

        var actualTimeLeft = timedControl.getTimeLeft(clock);

        assertEquals(expectTimeLeft, actualTimeLeft);
    }

    @Test
    void givenZonedDateTime_whenGetTimeLeft_thenReturn23SecondsTimeLeft() {
        String expectTimeLeft = "23 s";

        Clock clock = Clock.fixed(
                Instant.parse("2024-12-26T02:59:59Z"),
                ZoneOffset.UTC
        );

        TimedMark timedControl = new TimedMark(
            ZonedDateTime.parse("2024-12-26T00:00:22-03:00[America/Sao_Paulo]")
        );

        var actualTimeLeft = timedControl.getTimeLeft(clock);

        assertEquals(expectTimeLeft, actualTimeLeft);
    }
}
