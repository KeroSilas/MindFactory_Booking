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
            ResultSet rs = ps.executeQuery();

            Booking booking;
            while (rs.next()) {
                int bookingID = rs.getInt(1);
                int packageID = rs.getInt(2);
                int activityID = rs.getInt(3);
                int organizationID = rs.getInt(4);
                String firstName = rs.getString(5);
                String lastName = rs.getString(6);
                String position = rs.getString(7);
                String phone = rs.getString(8);
                String email = rs.getString(9);
                String åbenSkoleForløb = rs.getString(10);
                String transportType = rs.getString(11);
                String transportArrival = rs.getString(12);
                String transportDeparture = rs.getString(13);
                int participants = rs.getInt(14);
                LocalDateTime startDate = rs.getTimestamp(15).toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp(16).toLocalDateTime();
                LocalDateTime bookingDate = rs.getTimestamp(17).toLocalDateTime();
                boolean temporaryBooking = rs.getBoolean(18);
                String assistance = rs.getString(19);
                boolean noShow = rs.getBoolean(20);
                String messageToAS = rs.getString(21);
                String personalNote = rs.getString(22);


                booking = new Booking(int bookingID, int packageID, int activityID, int organizationID, String firstName, String lastName, String position, String phone, String email, String åbenSkoleForløb, String transportType, String transportArrival, String transportDeparture, int participants, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime bookingDate, boolean temporaryBooking, String assistance, boolean noShow, String messageToAS, String personalNote);
                bookings.add(booking);
            }

        } catch (SQLException e) {
            System.err.println("cannot access records (StationDaoImpl)");

        }
        return bookings;
    }


    @Override
    public void deleteBooking(Booking booking) {
            try (Connection con = databaseConnector.getConnection()) {
                PreparedStatement ps = con.prepareStatement("DELETE FROM Booking WHERE bookingID = ?;");
                ps.setInt(1, (booking.getbookingID()));
                ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Cannot delete Booking");
        }
    }
}
