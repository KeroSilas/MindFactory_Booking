package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.BookingTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
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
    public void bookingTimeList(List<BookingTime> bookingTimes, int bookingID, Date startDate, Time startTime, Time endTime) {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO BookingTime VALUES(?,?,?,?);");
            ps.setInt(1, bookingID);
            ps.setDate(2, startDate);
            ps.setTime(3, startTime);
            ps.setTime(4, endTime);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record (BookingTimes) " + e.getMessage());
        }
    }
}
