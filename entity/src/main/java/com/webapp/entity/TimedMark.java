package com.webapp.entity;

import java.time.*;
import java.util.Objects;

public class TimedMark {
    protected final ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/Sao_Paulo");
    private ZonedDateTime expiration;

    public TimedMark(ZonedDateTime zonedDateTime) {
        Objects.requireNonNull(zonedDateTime);
        this.expiration = convertToDefaultUTC(zonedDateTime);
    }

    public TimedMark(OffsetDateTime offsetDateTime) {
        Objects.requireNonNull(offsetDateTime);
        this.expiration = offsetDateTime.atZoneSameInstant(DEFAULT_ZONE_ID);
    }

    private ZonedDateTime convertToDefaultUTC(ZonedDateTime zonedDateTime) {
        return zonedDateTime.withZoneSameInstant(DEFAULT_ZONE_ID);
    }

    public OffsetDateTime getExpiration() {
        return expiration.toOffsetDateTime();
    }

    public LocalDate getExpirationDate() {
        return expiration.toLocalDate();
    }

    public LocalTime getExpirationTime() {
        return expiration.toLocalTime();
    }

    public LocalDateTime getExpirationLocalDateTime() {
        return expiration.toLocalDateTime();
    }

    public boolean isExpired() {
        ZonedDateTime now = ZonedDateTime.now(DEFAULT_ZONE_ID);
        return now.isAfter(expiration);
    }

    public boolean isExpired(Clock clock) {
        ZonedDateTime now = ZonedDateTime.now(clock);
        return now.isAfter(expiration);
    }

    public String getTimeLeft() {
        ZonedDateTime now = ZonedDateTime.now(DEFAULT_ZONE_ID);
        Duration duration = Duration.between(now, expiration);
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long seconds = duration.toSeconds();

        if (days > 0) {
            return days + "d";
        }

        if (hours > 0) {
            return (hours % 24) + "h";
        }

        if(minutes > 0) {
            return (minutes % 60) + "min";
        }

        return seconds > 0 ? seconds + "s" : "0s";
    }

    public String getTimeLeft(Clock clock) {
        ZonedDateTime now = ZonedDateTime.now(clock);
        Duration duration = Duration.between(now, expiration);
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long seconds = duration.toSeconds();

        if (days > 0) {
            return days + " d";
        }

        if (hours > 0) {
            return (hours % 24) + " h";
        }

        if(minutes > 0) {
            return (minutes % 60) + " min";
        }

        return seconds > 0 ? seconds + " s" : "0s";
    }

}
