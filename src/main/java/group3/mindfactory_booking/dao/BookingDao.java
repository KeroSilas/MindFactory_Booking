package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingEmail;
import group3.mindfactory_booking.model.singleton.Booking;

import java.util.List;


public interface BookingDao {

    public void saveBooking(Booking booking);

    List<Integer> getAllBookingID();

    List<BookingEmail> getOneWeekOutBookings();

    public void deleteBooking(int bookingID);

    }


