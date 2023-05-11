package group3.mindfactory_booking.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingTime {

    private final LocalDate startDate;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final boolean isWholeDay;
    private final boolean isHalfDayEarly;

    public BookingTime(LocalDate startDate, LocalTime startTime, LocalTime endTime, boolean isWholeDay, boolean isHalfDayEarly) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isWholeDay = isWholeDay;
        this.isHalfDayEarly = isHalfDayEarly;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isWholeDay() {
        return isWholeDay;
    }

    public boolean isHalfDayEarly() {
        return isHalfDayEarly;
    }
}
