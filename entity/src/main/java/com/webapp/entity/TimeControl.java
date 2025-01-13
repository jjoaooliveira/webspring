package com.webapp.entity;

import java.time.*;
import java.util.Objects;

public class TimeControl {
    protected final ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/Sao_Paulo");
    private ZonedDateTime creation;

    public TimeControl(String creation, String zone) {
        Objects.requireNonNull(creation);
        this.creation = convertToZonedDateTime(creation, zone);
    }

    public TimeControl(String creation) {
        Objects.requireNonNull(creation);
        this.creation = ZonedDateTime.parse(creation);
    }

    protected ZonedDateTime convertToZonedDateTime(String inputTimeString, String inputZone) {
        ZoneId zone = ZoneId.of(inputZone);
        LocalDateTime localDateTime = LocalDateTime.parse(inputTimeString);
        ZonedDateTime convertedZonedDateTime = ZonedDateTime.of(localDateTime, zone);
        return convertUtcToDefault(convertedZonedDateTime);
    }

    protected ZonedDateTime convertUtcToDefault(ZonedDateTime target) {
        return target.withZoneSameInstant(DEFAULT_ZONE_ID);
    }

    public String getCreation() {
        return creation.toString();
    }

    public String getCreationDate() {
        LocalDate creationLocalDate = creation.toLocalDate();
        return creationLocalDate.toString();
    }

    public String getCreationTime() {
        LocalTime creationLocalTime = creation.toLocalTime();
        return creationLocalTime.toString();
    }

    public LocalDateTime getCreationLocalDateTime() {
        return creation.toLocalDateTime();
    }
}
