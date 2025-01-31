package com.webapp.entity;

import com.webapp.entity.api.TimeMarkAPI;
import com.webapp.entity.api.TimedMarkAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TimeMarkTest {

    @Test
    void givenOffsetDateTime_whenGetCreation_thenReturnStringCreation() {
        //arrange
        String expectCreation = "2024-12-25T15:30-03:00";

        //act
        var actual = new TimeMarkAPI(OffsetDateTime.parse("2024-12-25T15:30-03:00"));

        //assert
        assertEquals(expectCreation, actual.getOffsetDateTimeCreation().toString());
    }

    @Test
    void givenNoArgs_whenCreatingTimeMark_thenShouldCreateTimeMark() {
        //arrange

        //act
        var actualTimeMark = new TimeMarkAPI();

        //assert
        assertNotNull(actualTimeMark);
    }
}
