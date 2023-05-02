package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingFilesDaoImpl implements BookingFilesDao{
    private final DatabaseConnector databaseConnector;

    public BookingFilesDaoImpl() {
        databaseConnector = new DatabaseConnector();
    }
    @Override
    public void addBookingFiles(int bookingID, int fileID) {
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO BookingFiles VALUES(?,?);");
            ps.setInt(1, bookingID);
            ps.setInt(2, fileID);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record (BookingFiles) " + e.getMessage());
        }

    }

    @Override
    public void deleteBookingFiles(File file) {

    }


    public List<File> getAllFilesOnBookingFile(Booking booking) {
        List<File> files = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Booking, BookingFiles, Files WHERE Booking.bookingID = " +
                    "BookingFiles.bookingID AND BookingFiles.fileID = Files.fileID;");
            ResultSet rs = ps.executeQuery();

            File file;
            while (rs.next()) {
                int fileID = rs.getInt(1);
                String filePath = rs.getString(2);
                String fileName = rs.getString(3);

                file = new File(fileID, filePath,fileName);
                files.add(file);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllFiles (BookingFilesDaoImpl) " + e.getMessage());
        }
        return files;
    }
}
