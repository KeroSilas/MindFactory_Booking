package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Booking;

import java.sql.*;
import java.time.LocalDateTime;


public class BookingDaoImpl implements BookingDao {

    private final DatabaseConnector databaseConnector;

    public BookingDaoImpl() {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public void saveBooking(int bookingID, int packageID, int activityID, int organizationID, String firstName, String lastName, String position, String phone, String email, String åbenSkoleForløb, String transportType, String transportArrival, String transportDeparture, int participants, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime bookingDate, boolean temporaryBooking, String assistance, boolean noShow, String messageToAS, String personalNote) {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO MOVIE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

            ps.setInt(1, bookingID);
            ps.setInt(2, packageID);
            ps.setInt(3, activityID);
            ps.setInt(4, organizationID);
            ps.setString(5, firstName);
            ps.setString(6, lastName);
            ps.setString(7, position);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, åbenSkoleForløb);
            ps.setString(11, transportType);
            ps.setString(12, transportArrival);
            ps.setString(13, transportDeparture);
            ps.setInt(14, participants);
            ps.setTimestamp(15, Timestamp.valueOf(startDate));
            ps.setTimestamp(16, Timestamp.valueOf(endDate));
            ps.setTimestamp(17, Timestamp.valueOf(bookingDate));
            ps.setBoolean(18, temporaryBooking);
            ps.setString(19, assistance);
            ps.setBoolean(20, noShow);
            ps.setString(21, messageToAS);
            ps.setString(22, personalNote);


        } catch (SQLException e) {
            System.err.println("cannot access records (StationDaoImpl)");

        }


    }

    @Override
    public void deleteBooking(Booking booking) {

    }

}