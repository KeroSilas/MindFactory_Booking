package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingTime;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

public interface BookingTimesDao {

    void addToBookingTimes(int bookingID, int equipmentID);

    void removeFromBookingTimes(int equipmentID);

    List<BookingTime> getBookingTimeList();

}
