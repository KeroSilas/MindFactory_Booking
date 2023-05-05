package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.singleton.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookingDaoImpl implements BookingDao {

    private final DatabaseConnector databaseConnector;

    public BookingDaoImpl() {
        databaseConnector = DatabaseConnector.getInstance();
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
            ps.setString(12, booking.getAssistance());
            ps.setString(13, booking.getTransportType());
            ps.setString(14, booking.getTransportArrival());
            ps.setString(15, booking.getTransportDeparture());
            ps.setInt(16, booking.getParticipants());
            ps.setDate(17, Date.valueOf(booking.getDate()));
            ps.setTime(18, Time.valueOf(booking.getStartTime()));
            ps.setTime(19, Time.valueOf(booking.getEndTime()));
            ps.setTimestamp(20, Timestamp.valueOf(booking.getBookingDateTime()));
            ps.setBoolean(21, booking.isWholeDay());
            ps.setBoolean(22, booking.isNoShow());
            ps.setString(23, booking.getMessageToAS());
            ps.setString(24, booking.getPersonalNote());
            ps.setString(25, booking.getBookingType());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getAllBookingID() {

        List<Integer> bookingIDs = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT bookingID FROM Booking;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int checkedID = rs.getInt(1);
                bookingIDs.add(checkedID);
            }

        } catch (SQLException e) {
            System.err.println("cannot access Bookings (BookingDaoImpl) " + e.getMessage());
        }

        return bookingIDs;
    }

    @Override
    public void deleteBooking(int bookingID) throws RuntimeException {
        int rowsAffected;
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Booking WHERE BookingID = ?");
            ps.setInt(1, bookingID);
            rowsAffected = ps.executeUpdate(); // https://stackoverflow.com/questions/2571915/return-number-of-rows-affected-by-sql-update-statement-in-java

            if (rowsAffected == 0) {
                throw new RuntimeException("Booking with ID " + bookingID + " does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}