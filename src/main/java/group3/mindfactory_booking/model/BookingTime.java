package group3.mindfactory_booking.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingTime {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isWholeDay;
    private boolean isNoShow;

    public BookingTime(LocalDate date, LocalTime startTime, LocalTime endTime, boolean isWholeDay, boolean isNoShow) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isWholeDay = isWholeDay;
        this.isNoShow = isNoShow;
    }

    public LocalDate getDate() {
        return date;
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

    public boolean isNoShow() {
        return isNoShow;
    }

    @Override
    public String toString() {
        return date + " " + startTime + " - " + endTime;
    }
}
