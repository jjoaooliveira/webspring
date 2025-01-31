import com.webapp.entity.TimeMark;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TimeMarkTest {
    @Test
    void givenOffsetDateTime_whenGetCreation_thenReturnStringCreation() {
        String expectCreation = "2024-12-25T15:30-03:00";

        TimeMark timeMark = new TimeMark(OffsetDateTime.parse("2024-12-25T15:30-03:00"));
        var actualCreation = timeMark.getCreation();

        assertEquals(expectCreation, actualCreation.toString());
    }

    @Test
    void givenNoArgs_whenCreatingTimeMark_thenShouldCreateTimeMark() {
        TimeMark actualTimeMark = new TimeMark();

        assertNotNull(actualTimeMark);
    }
}
