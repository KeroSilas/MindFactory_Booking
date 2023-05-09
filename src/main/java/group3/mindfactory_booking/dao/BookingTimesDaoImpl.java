package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class BookingTimesDaoImpl implements BookingTimesDao{
    private final DatabaseConnector databaseConnector;

    public BookingTimesDaoImpl() {
        databaseConnector = DatabaseConnector.getInstance();
    }


    @Override
    public void addToBookingTimes(int bookingID, int equipmentID) {

    }

    @Override
    public void removeFromBookingTimes(int equipmentID) {

    }


    @Override
    public void saveBookingTimeList(List<BookingTime> bookingTimes, int bookingID) {
        try (Connection con = databaseConnector.getConnection()){
            con.setAutoCommit(false);
            String sql = ("INSERT INTO BookingTimes VALUES(?,?,?,?,?,?);");
            PreparedStatement ps = con.prepareStatement(sql);

            for (BookingTime bookingTime : bookingTimes) {
                ps.setInt(1, bookingID);
                ps.setDate(2, Date.valueOf(bookingTime.getDate()));
                ps.setTime(3, Time.valueOf(bookingTime.getStartTime()));
                ps.setTime(4, Time.valueOf(bookingTime.getEndTime()));
                ps.setBoolean(5, bookingTime.isWholeDay());
                ps.setBoolean(6, bookingTime.isNoShow());

                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
