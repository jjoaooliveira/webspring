package com.webapp.entity;

import java.time.*;
import java.util.Objects;

public class TimedControl extends TimeControl {
    protected ZonedDateTime expiration;

    public TimedControl(String creation, String expiration, String zone) {
        super(creation, zone);
        Objects.requireNonNull(expiration);
        this.expiration = convertToZonedDateTime(expiration, zone);
    }

    public void setExpiration(String newExpiration, String zone) {
        this.expiration = convertToZonedDateTime(newExpiration, zone);
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
        ZonedDateTime now = ZonedDateTime.now(DEFAULT_ZONE_ID);
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

}
