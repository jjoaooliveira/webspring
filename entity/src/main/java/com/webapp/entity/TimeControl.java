package com.webapp.entity;

import java.time.*;
import java.util.Objects;

public class TimeControl {
    protected final ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/Sao_Paulo");
    private ZonedDateTime creation;

    public TimeControl() {
        this.creation = ZonedDateTime.now(DEFAULT_ZONE_ID);
    }

    public TimeControl(String creation) {
        Objects.requireNonNull(creation);
        this.creation = ZonedDateTime.parse(creation);
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
