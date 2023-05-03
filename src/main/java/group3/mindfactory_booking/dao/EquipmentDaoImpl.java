package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDaoImpl implements EquipmentDao{
    private final DatabaseConnector databaseConnector;

    public EquipmentDaoImpl() {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public void saveEquipment(Equipment equipment) {

        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO Equipment VALUES(?,?);");
            ps.setInt(1, equipment.getEquipmentID());
            ps.setString(2, equipment.getEquipmentName());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record: " + e.getMessage());
        }

    }

    @Override
    public void deleteEquipment(int equipmentID) {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Equipment WHERE equipmentID = ?");
            ps.setInt(1, equipmentID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Equipment> getAllEquipment() {
        List<Equipment> equipments = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Equipment;");
            ResultSet rs = ps.executeQuery();

            Equipment equipment;
            while (rs.next()) {
                int equipmentID = rs.getInt(1);
                String equipmentName = rs.getString(2);

                equipment = new Equipment(equipmentID, equipmentName);
                equipments.add(equipment);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllMovies (MovieDaoImpl) " + e.getMessage());
        }
        return equipments;
    }


}
