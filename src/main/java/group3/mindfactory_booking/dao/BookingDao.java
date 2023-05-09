package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingEmail;
import group3.mindfactory_booking.model.singleton.Booking;

import java.util.List;


public interface BookingDao {

    void saveBooking(Booking booking);

    List<Integer> getAllBookingID();

    List<BookingEmail> getOneWeekOutBookings();

    void deleteBooking(int bookingID);

    }


