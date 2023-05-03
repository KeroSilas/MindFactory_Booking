package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.singleton.Booking;


public interface BookingDao {


        public void saveBooking(Booking booking);


        public void deleteBooking(int bookingID);
    }


