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
    public void saveBooking(Booking booking) throws RuntimeException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Booking VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

            ps.setInt(1, booking.getBookingID());
            ps.setInt(2, booking.getCatering().getPackageID());
            ps.setInt(3, booking.getActivity().getActivityID());
            ps.setInt(4, booking.getOrganization().getOrganizationID());
            ps.setString(5, booking.getFirstName());
            ps.setString(6, booking.getLastName());
            ps.setString(7, booking.getPosition());
            ps.setString(8, booking.getPhone());
            ps.setString(9, booking.getEmail());
            ps.setString(10, booking.getÅbenSkoleForløb());
            ps.setString(11, booking.getTransportType());
            ps.setString(12, booking.getTransportArrival());
            ps.setString(13, booking.getTransportDeparture());
            ps.setInt(14, booking.getParticipants());
            ps.setTimestamp(15, Timestamp.valueOf(booking.getStartDate()));
            ps.setTimestamp(16, Timestamp.valueOf(booking.getEndDate()));
            ps.setTimestamp(17, Timestamp.valueOf(booking.getBookingDate()));
            ps.setBoolean(18, booking.isTemporaryBooking());
            ps.setString(19, booking.getAssistance());
            ps.setBoolean(20, booking.isNoShow());
            ps.setString(21, booking.getMessageToAS());
            ps.setString(22, booking.getPersonalNote());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deleteBooking(int bookingID) throws RuntimeException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Booking WHERE BookingID = ?");
            ps.setInt(1, bookingID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}