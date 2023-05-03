package group3.mindfactory_booking.dao;
import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.Equipment;

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
    public void addToBookingEquipment(int bookingID, int equipmentID){
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO BookingEquipment VALUES(?,?);");
            ps.setInt(1, bookingID);
            ps.setInt(2, equipmentID);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record (BookingEquipment) " + e.getMessage());
        }
    }

    @Override
    public void removeFromBookingEquipment(int equipmentID) {
        try (Connection con = databaseConnector.getConnection()){

        PreparedStatement ps = con.prepareStatement("DELETE FROM BookingEquipment WHERE equipmentID = ?;");
        ps.setInt(1, equipmentID);

        ps.executeUpdate();

        } catch (SQLException e) {
        System.err.println("Cannot delete equipment (BookingEquipment) " + e.getMessage());
        }
    }


    public List<Equipment> getAllEquipmentOnBookingEquipment(Booking booking){
        List<Equipment> equipments = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Booking, BookingEquipment, Equipment WHERE Booking.bookingID = " +
                    "BookingEquipment.bookingID AND BookingEquipment.equipmentID = Equipment.equipmentID;");
            ResultSet rs = ps.executeQuery();

            Equipment equipment;
            while (rs.next()) {
                int equipmentID = rs.getInt(1);
                String equipmentName = rs.getString(2);

                equipment = new Equipment(equipmentID, equipmentName);
                equipments.add(equipment);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllEquipment (BookingEquipmentDaoImpl) " + e.getMessage());
        }
        return equipments;
    }
}


