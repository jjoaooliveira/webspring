package com.webapp.entity.api;

import com.webapp.entity.TimeMark;

import java.time.OffsetDateTime;

public class TimeMarkAPI {
    private TimeMark timeMark;

    public TimeMarkAPI(OffsetDateTime offsetDateTime) {
        this.timeMark = new TimeMark(offsetDateTime);
    }

    public OffsetDateTime getOffsetDateTimeCreation() {
        return timeMark.getCreation();
    }
}
