package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Booking;

import java.time.LocalDateTime;


public interface BookingDao {


        public void saveBooking(int bookingID, int packageID, int activityID, int organizationID, String firstName, String lastName, String position, String phone, String email, String åbenSkoleForløb, String transportType, String transportArrival, String transportDeparture, int participants, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime bookingDate, boolean temporaryBooking, String assistance, boolean noShow, String messageToAS, String personalNote);


        public void deleteBooking(Booking booking);
    }


