package com.webapp.entity;

import java.time.*;
import java.util.Objects;

public class TimedMark {
    protected final ZoneId TARGET_ZONE_ID = ZoneId.of("America/Sao_Paulo");
    private ZonedDateTime expiration;

    public TimedMark(String expiration, String zone) {
        Objects.requireNonNull(expiration);
        this.expiration = convertToZonedDateTime(expiration, zone);
    }

    protected ZonedDateTime convertToZonedDateTime(String inputTimeString, String inputZone) {
        ZoneId zone = ZoneId.of(inputZone);
        LocalDateTime localDateTime = LocalDateTime.parse(inputTimeString);
        ZonedDateTime convertedZonedDateTime = ZonedDateTime.of(localDateTime, zone);
        return convertUtcToDefault(convertedZonedDateTime);
    }

    private ZonedDateTime convertUtcToDefault(ZonedDateTime target) {
        return target.withZoneSameInstant(TARGET_ZONE_ID);
    }

    public String getExpiration() {
        return expiration.toString();
    }

    public String getExpirationDate() {
        LocalDate expirationLocalDate = expiration.toLocalDate();
        return expirationLocalDate.toString();
    }

    public String getExpirationTime() {
        LocalTime expirationLocalTime = expiration.toLocalTime();
        return expirationLocalTime.toString();
    }

    public LocalDateTime getExpirationLocalDateTime() {
        return expiration.toLocalDateTime();
    }

    public boolean isExpired() {
        ZonedDateTime now = ZonedDateTime.now(TARGET_ZONE_ID);
        return now.isAfter(expiration);
    }

    public String getTimeLeft() {
        ZonedDateTime now = ZonedDateTime.now(TARGET_ZONE_ID);
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

}
