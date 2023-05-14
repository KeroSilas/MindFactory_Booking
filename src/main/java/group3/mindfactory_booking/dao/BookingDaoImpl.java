package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingTime;
import group3.mindfactory_booking.model.singleton.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class BookingDaoImpl implements BookingDao {

    private final DatabaseConnector databaseConnector;

    public BookingDaoImpl() {
        databaseConnector = DatabaseConnector.getInstance();
    }

    // https://stackoverflow.com/questions/7877747/closing-a-database-connection-in-java
    @Override
    public void saveBooking(Booking booking) throws SQLException {
        Connection con = databaseConnector.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?,?,?);");

            ps.setString(1, booking.getCustomer().getFirstName());
            ps.setString(2, booking.getCustomer().getLastName());
            ps.setString(3, booking.getCustomer().getPhone());
            ps.setString(4, booking.getCustomer().getEmail());
            ps.setString(5, booking.getCustomer().getDepartment());
            ps.setString(6, booking.getCustomer().getPosition());
            ps.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement("INSERT INTO Booking VALUES(?,?,?,?,?,?,?,?,?,?,?);");

            ps2.setInt(1, booking.getBookingID());
            ps2.setString(2, booking.getCustomer().getEmail());
            ps2.setTimestamp(3, Timestamp.valueOf(booking.getBookingDateTime()));
            ps2.setDate(4, Date.valueOf(booking.getStartDate()));
            ps2.setTime(5, Time.valueOf(booking.getStartTime()));
            ps2.setTime(6, Time.valueOf(booking.getEndTime()));
            ps2.setBoolean(7, booking.isWholeDay());
            ps2.setBoolean(8, booking.isNoShow());
            ps2.setBoolean(9, booking.isEmailSent());
            ps2.setString(10, booking.getMessageToAS());
            ps2.setString(11, booking.getPersonalNote());
            ps2.executeUpdate();

            if (booking.getCatering() != null) {
                PreparedStatement ps3 = con.prepareStatement("INSERT INTO BookingCatering VALUES(?,?);");
                ps3.setInt(1, booking.getBookingID());
                ps3.setInt(2, booking.getCatering().getCateringID());
                ps3.executeUpdate();
            }

            if (booking.getActivity() != null) {
                PreparedStatement ps4 = con.prepareStatement("INSERT INTO BookingActivity VALUES(?,?);");
                ps4.setInt(1, booking.getBookingID());
                ps4.setInt(2, booking.getActivity().getActivityID());
                ps4.executeUpdate();
            }

            if (booking.getOrganization() != null) {
                PreparedStatement ps5 = con.prepareStatement("INSERT INTO BookingOrganisation VALUES(?,?,?,?);");
                ps5.setInt(1, booking.getBookingID());
                ps5.setInt(2, booking.getOrganization().getOrganizationID());
                ps5.setString(3, booking.getOrganization().getAssistance());
                ps5.setInt(4, booking.getOrganization().getParticipants());
                ps5.executeUpdate();
            }

            if (booking.getÅbenSkoleForløb() != null) {
                PreparedStatement ps6 = con.prepareStatement("INSERT INTO BookingForløb VALUES(?,?,?,?,?);");
                ps6.setInt(1, booking.getBookingID());
                ps6.setInt(2, booking.getÅbenSkoleForløb().getForløbID());
                ps.setString(3, booking.getÅbenSkoleForløb().getTransportType());
                ps.setString(4, booking.getÅbenSkoleForløb().getTransportArrival());
                ps.setString(5, booking.getÅbenSkoleForløb().getTransportDeparture());
                ps6.executeUpdate();
            }

            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException e) {
            con.rollback();
            throw new SQLException(e);
        } finally {
            con.close();
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
    public List<BookingTime> getBookingTimeList() {
        List<BookingTime> bookingTimeList = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT startDate, startTime, endTime, isWholeDay FROM Booking;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                LocalTime startTime = rs.getTime("startTime").toLocalTime();
                LocalTime endTime = rs.getTime("endTime").toLocalTime();
                boolean isWholeDay = rs.getBoolean("isWholeDay");
                bookingTimeList.add(new BookingTime(startDate, startTime, endTime, isWholeDay));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingTimeList;
    }

    @Override
    public void deleteBooking(int bookingID) throws SQLException {
        int rowsAffected;
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Booking WHERE bookingID = ?");
            ps.setInt(1, bookingID);
            rowsAffected = ps.executeUpdate(); // https://stackoverflow.com/questions/2571915/return-number-of-rows-affected-by-sql-update-statement-in-java

            if (rowsAffected == 0) {
                throw new SQLException("Booking with ID " + bookingID + " does not exist");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}