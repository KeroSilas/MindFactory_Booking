package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingTime;
import group3.mindfactory_booking.model.singleton.Booking;

import java.sql.SQLException;
import java.util.List;


public interface BookingDao {

    void saveBooking(Booking booking) throws SQLException;

    List<Integer> getAllBookingID();

    List<BookingTime> getBookingTimeList();

    void deleteBooking(int bookingID) throws SQLException;

}


