package group3.mindfactory_booking.dao;
import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.Equipment;
import group3.mindfactory_booking.model.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingEquipmentDaoImpl implements BookingEquipmentDao{
    private final DatabaseConnector databaseConnector;

    public BookingEquipmentDaoImpl() {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public void addBookingEquip(int bookingID, int equipmentID){
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO BookingEquipment VALUES(?,?);");
            ps.setInt(1, bookingID);
            ps.setInt(2, equipmentID);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record (BookingEquipment) " + e.getMessage());
        }

    };

    @Override
    public void removeFromBookingEquip(Equipment equipment) {

    }


/*
    List<Equipment> getAllEquipmentOnBookingEquip(Booking booking){
        List<File> files = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM BookingEquip;");
            ResultSet rs = ps.executeQuery();

            File file;
            while (rs.next()) {
                int fileID = rs.getInt(1);
                String filePath = rs.getString(2);
                String fileName = rs.getString(3);

                file = new File(fileID, filePath, fileName);
                files.add(file);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllFiles (FileDaoImpl) " + e.getMessage());
        }
        return files;

      }  */
    }


