package me.xcue.hylib.lib.util.datetime

import java.time.Duration
import java.time.ZonedDateTime

object DateTimeUtil {
    fun timeSince(time: ZonedDateTime): Duration {
        return Duration.between(time, ZonedDateTime.now())
    }
}