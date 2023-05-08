package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingTime;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

public interface BookingTimesDao {

    public void addToBookingTimes(int bookingID, int equipmentID);

    public void removeFromBookingTimes(int equipmentID);


    public void bookingTimeList(List<BookingTime> bookingTimes, int bookingID, Date startDate, Time startTime, Time endTime);

}
