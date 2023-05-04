package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.singleton.Booking;

import java.sql.*;


public class BookingDaoImpl implements BookingDao {

    private final DatabaseConnector databaseConnector;

    public BookingDaoImpl() {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public void saveBooking(Booking booking) throws RuntimeException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Booking VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

            ps.setInt(1, booking.getBookingID());
            ps.setInt(2, booking.getCatering().getPackageID());
            ps.setInt(3, booking.getActivity().getActivityID());
            ps.setInt(4, booking.getOrganization().getOrganizationID());
            ps.setInt(5, booking.getÅbenSkoleForløb().getForløbID());
            ps.setString(6, booking.getFirstName());
            ps.setString(7, booking.getLastName());
            ps.setString(8, booking.getPosition());
            ps.setString(9, booking.getAfdeling());
            ps.setString(10, booking.getPhone());
            ps.setString(11, booking.getEmail());
            ps.setString(12, booking.getTransportType());
            ps.setString(13, booking.getTransportArrival());
            ps.setString(14, booking.getTransportDeparture());
            ps.setInt(15, booking.getParticipants());
            ps.setDate(16, Date.valueOf(booking.getDate()));
            ps.setTime(17, Time.valueOf(booking.getStartTime()));
            ps.setTime(18, Time.valueOf(booking.getEndTime()));
            ps.setTimestamp(19, Timestamp.valueOf(booking.getBookingDateTime()));
            ps.setBoolean(20, booking.isWholeDay());
            ps.setBoolean(21, booking.isNoShow());
            ps.setString(22, booking.getMessageToAS());
            ps.setString(23, booking.getPersonalNote());
            ps.setString(24, booking.getAssistance());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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