package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingEmail;
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
            PreparedStatement ps = con.prepareStatement("INSERT INTO Booking VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

            ps.setInt(1, booking.getBookingID());
            ps.setString(2, booking.getBookingType());
            ps.setString(3, booking.getCatering());
            ps.setString(4, booking.getActivity());
            ps.setString(5, booking.getOrganization());
            ps.setString(6, booking.getÅbenSkoleForløb());
            ps.setString(7, booking.getFirstName());
            ps.setString(8, booking.getLastName());
            ps.setString(9, booking.getPosition());
            ps.setString(10, booking.getAfdeling());
            ps.setString(11, booking.getPhone());
            ps.setString(12, booking.getEmail());
            ps.setString(13, booking.getAssistance());
            ps.setString(14, booking.getTransportType());
            ps.setString(15, booking.getTransportArrival());
            ps.setString(16, booking.getTransportDeparture());
            ps.setInt(17, booking.getParticipants());
            ps.setTimestamp(18, Timestamp.valueOf(booking.getBookingDateTime()));
            ps.setDate(19, Date.valueOf(booking.getStartDate()));
            ps.setTime(20, Time.valueOf(booking.getStartTime()));
            ps.setTime(21, Time.valueOf(booking.getEndTime()));
            ps.setBoolean(22, booking.isWholeDay());
            ps.setBoolean(23, booking.isHalfDayEarly());
            ps.setBoolean(24, booking.isNoShow());
            ps.setBoolean(25, booking.isEmailSent());
            ps.setString(26, booking.getMessageToAS());
            ps.setString(27, booking.getPersonalNote());
            ps.executeUpdate();

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
            PreparedStatement ps = con.prepareStatement("SELECT startDate, startTime, endTime, isWholeDay, isHalfDayEarly FROM Booking;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                LocalTime startTime = rs.getTime("startTime").toLocalTime();
                LocalTime endTime = rs.getTime("endTime").toLocalTime();
                boolean isWholeDay = rs.getBoolean("isWholeDay");
                boolean isHalfDayEarly = rs.getBoolean("isHalfDayEarly");
                bookingTimeList.add(new BookingTime(startDate, startTime, endTime, isWholeDay, isHalfDayEarly));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingTimeList;
    }

    // https://stackoverflow.com/questions/36296140/subtract-two-dates-in-microsoft-sql-server
    // https://stackoverflow.com/questions/37559741/convert-timestamp-to-date-in-oracle-sql
    // https://www.sqlservercentral.com/articles/the-output-clause-for-update-statements
    @Override
    public List<BookingEmail> getOneWeekOutBookings() {

        List<BookingEmail> oneWeekOutBookings = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Booking " +
                    "SET isEmailSent = 1 " +
                    "OUTPUT INSERTED.bookingID, INSERTED.firstName, INSERTED.email, INSERTED.startDate " +
                    "FROM Booking " +
                    "WHERE DATEDIFF(day, CAST(GETDATE() AS DATE), startDate) < 7 AND isEmailSent = 0;"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                BookingEmail bookingEmail;
                int bookingID = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                LocalDate startDate = rs.getDate(4).toLocalDate();

                bookingEmail = new BookingEmail(bookingID, name, email, startDate);
                oneWeekOutBookings.add(bookingEmail);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return oneWeekOutBookings;
    }

    @Override
    public void deleteBooking(int bookingID) throws SQLException {
        int rowsAffected;
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Booking WHERE BookingID = ?");
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