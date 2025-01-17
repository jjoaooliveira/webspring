import com.webapp.entity.TimeMark;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

public class TimeMarkTest {
    @Test
    void givenStringCreation_whenGetCreation_thenReturnStringCreation() {
        String expectCreation = "2024-12-25T15:30-03:00[America/Sao_Paulo]";

        TimeMark timeMark = new TimeMark("2024-12-25T15:30-03:00[America/Sao_Paulo]");
        String actualCreation = timeMark.getCreation();

        assertEquals(expectCreation, actualCreation);
    }

    @Test
    void givenInvalidStringDateTime_whenCreatingTimeMark_thenThrownDateTimeException() {
        assertThrows(DateTimeException.class, () -> new TimeMark("Lorem"));
    }

    @Test
    void givenNoArgs_whenCreatingTimeMark_thenShouldCreateTimeMark() {
        TimeMark actualTimeMark = new TimeMark();

        assertNotNull(actualTimeMark);
    }
}
