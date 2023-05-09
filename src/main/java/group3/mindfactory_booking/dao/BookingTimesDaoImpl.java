package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingTime;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
            String sql = ("INSERT INTO BookingTimes VALUES(?,?,?,?,?,?,?);");
            PreparedStatement ps = con.prepareStatement(sql);

            for (BookingTime bookingTime : bookingTimes) {
                ps.setInt(1, bookingID);
                ps.setDate(2, Date.valueOf(bookingTime.getDate()));
                ps.setTime(3, Time.valueOf(bookingTime.getStartTime()));
                ps.setTime(4, Time.valueOf(bookingTime.getEndTime()));
                ps.setBoolean(5, bookingTime.isWholeDay());
                ps.setBoolean(6, bookingTime.isHalfDayEarly());
                ps.setBoolean(7, bookingTime.isNoShow());

                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookingTime> getBookingTimeList() {
        List<BookingTime> bookingTimeList = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT startDate, startTime, endTime, isWholeDay, isHalfDayEarly, isNoShow FROM BookingTimes;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                LocalTime startTime = rs.getTime("startTime").toLocalTime();
                LocalTime endTime = rs.getTime("endTime").toLocalTime();
                boolean isWholeDay = rs.getBoolean("isWholeDay");
                boolean isHalfDayEarly = rs.getBoolean("isHalfDayEarly");
                boolean isNoShow = rs.getBoolean("isNoShow");
                bookingTimeList.add(new BookingTime(startDate, startTime, endTime, isWholeDay, isHalfDayEarly, isNoShow));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingTimeList;
    }


}
