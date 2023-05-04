package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Booking;

import java.time.LocalDateTime;


public interface BookingDao {


        public void saveBooking(Booking booking);


        public void deleteBooking(int bookingID);

        public boolean checkBookingID(int randomNum);


}


