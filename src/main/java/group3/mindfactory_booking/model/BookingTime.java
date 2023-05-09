package group3.mindfactory_booking.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingTime {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isWholeDay;
    private boolean isHalfDayEarly;
    private boolean isNoShow;

    public BookingTime(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        isWholeDay = false;
        isHalfDayEarly = false;
        isNoShow = false;
    }

    public BookingTime(LocalDate date, LocalTime startTime, LocalTime endTime, boolean isWholeDay, boolean isHalfDayEarly, boolean isNoShow) {
        this(date, startTime, endTime);
        this.isWholeDay = isWholeDay;
        this.isHalfDayEarly = isHalfDayEarly;
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
        if (startTime.isBefore(LocalTime.of(12, 0)) && endTime.isAfter(LocalTime.of(12, 0))) {
            isWholeDay = true;
        }
        return isWholeDay;
    }

    public boolean isHalfDayEarly() {
        if (endTime.isBefore(LocalTime.of(12, 0))) {
            isHalfDayEarly = true;
        }
        return isHalfDayEarly;
    }

    public boolean isNoShow() {
        return isNoShow;
    }

    public void setNoShow(boolean isNoShow) {
        this.isNoShow = isNoShow;
    }

    public void setWholeDay(boolean isWholeDay) {
        this.isWholeDay = isWholeDay;
    }

    public void setHalfDayEarly(boolean isHalfDayEarly) {
        this.isHalfDayEarly = isHalfDayEarly;
    }

    @Override
    public String toString() {
        return date + " " + startTime + " - " + endTime;
    }
}
