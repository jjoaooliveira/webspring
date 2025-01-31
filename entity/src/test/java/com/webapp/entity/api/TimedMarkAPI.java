package com.webapp.entity.api;

import com.webapp.entity.TimedMark;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class TimedMarkAPI {
    TimedMark timedMark;

    public TimedMarkAPI(ZonedDateTime zonedDateTime) {
        this.timedMark = new TimedMark(zonedDateTime);
    }

    public TimedMarkAPI(OffsetDateTime offsetDateTime) {
        this.timedMark = new TimedMark(offsetDateTime);
    }

    public String timeLeft(Clock clock) {
        return timedMark.getTimeLeft(clock);
    }

    public OffsetDateTime expiration() {
        return timedMark.getExpiration();
    }

    public Boolean expired() {
        return timedMark.isExpired();
    }

    public Boolean expired(Clock clock) {
        return timedMark.isExpired(clock);
    }
}
