package group3.mindfactory_booking.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingTime {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public BookingTime(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return date + " " + startTime + " - " + endTime;
    }
}
