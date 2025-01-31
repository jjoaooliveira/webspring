package com.webapp.entity;

import com.webapp.entity.api.TimedMarkAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimedMarkTest {
    Clock clock;

    @BeforeEach
    void setUp() {
        clock = Clock.fixed(
                Instant.parse("2024-12-26T02:59:00Z"),
                ZoneOffset.UTC
        );
    }

    @Test
    @DisplayName("When Create TimedMark With Success Should Return TimedMark")
    void givenRioBrancoZonedDateTime_whenGetExpiration_thenReturnExpirationWithSaoPauloZone() {
        //arrange
        String expectExpiration = "2024-12-31T01:59-03:00";
        String date = "2024-12-30T23:59-05:00[America/Rio_Branco]";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        TimedMarkAPI timedMarkAPI = new TimedMarkAPI(zonedDateTime);

        //act
        var actual = timedMarkAPI.expiration();

        //assert
        assertEquals(expectExpiration, actual.toString());
    }

    @Test
    @DisplayName("When Create Expired TimedMark With Success Should Return Expired TimedMark")
    void givenZonedDateTime_whenIsExpired_thenReturnTrue() {
        //arrange
        Boolean expectExpired = true;
        String date = "2024-12-25T20:59-05:00[America/Rio_Branco]";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        TimedMarkAPI timedMarkAPI = new TimedMarkAPI(zonedDateTime);

        //act
        var actualExpired = timedMarkAPI.expired(clock);

        //assert
        assertEquals(expectExpired, actualExpired);
    }

    @Test
    @DisplayName("When Create TimedMark With 5 Days Left Should Return TimedMark")
    void givenZonedDateTime_whenGetTimeLeft_thenReturn5DaysTimeLeft() {
        //arrange
        String expectTimeLeft = "5 d";
        String date = "2024-12-30T23:59-03:00[America/Sao_Paulo]";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        TimedMarkAPI timedMarkAPI = new TimedMarkAPI(zonedDateTime);

        //act
        var actualTimeLeft = timedMarkAPI.timeLeft(clock);

        //assert
        assertEquals(expectTimeLeft, actualTimeLeft);
    }

    @Test
    @DisplayName("When Create TimedMark With 3 minutes Left Should Return TimedMark")
    void givenZonedDateTime_whenGetTimeLeft_thenReturn3HoursTimeLeft() {
        //arrange
        String expectTimeLeft = "3 min";
        String date = "2024-12-26T00:02-03:00[America/Sao_Paulo]";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        TimedMarkAPI timedMarkAPI = new TimedMarkAPI(zonedDateTime);

        //act
        var actualTimeLeft = timedMarkAPI.timeLeft(clock);

        //assert
        assertEquals(expectTimeLeft, actualTimeLeft);
    }

    @Test
    @DisplayName("When Create TimedMark With 23 Seconds Left Should Return TimedMark")
    void givenZonedDateTime_whenGetTimeLeft_thenReturn23SecondsTimeLeft() {
        //arrange
        String expectTimeLeft = "23 s";
        String date = "2024-12-25T23:59:23-03:00[America/Sao_Paulo]";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        TimedMarkAPI timedMarkAPI = new TimedMarkAPI(zonedDateTime);

        //act
        var actualTimeLeft = timedMarkAPI.timeLeft(clock);

        //assert
        assertEquals(expectTimeLeft, actualTimeLeft);
    }
}
