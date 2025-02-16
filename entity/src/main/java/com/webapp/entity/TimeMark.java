package com.webapp.entity;

import java.time.*;
import java.util.Objects;

public class TimeMark {
    protected final ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/Sao_Paulo");
    private ZonedDateTime creation;

    public TimeMark(OffsetDateTime creation) {
        this.creation = creation != null
                ? creation.atZoneSameInstant(DEFAULT_ZONE_ID)
                : ZonedDateTime.now(DEFAULT_ZONE_ID);
    }

    public OffsetDateTime getCreation() {
        return creation.toOffsetDateTime();
    }

    public LocalDate getCreationDate() {
        return creation.toLocalDate();
    }

    public LocalTime getCreationTime() {
        return creation.toLocalTime();
    }

    public LocalDateTime getCreationLocalDateTime() {
        return creation.toLocalDateTime();
    }
}
