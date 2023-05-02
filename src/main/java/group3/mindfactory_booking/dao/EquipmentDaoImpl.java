package group3.mindfactory_booking.dao;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EquipmentDaoImpl implements EquipmentDao{

        private final DatabaseConnector databaseConnector;

        public EquipmentDaoImpl() {
            databaseConnector = new DatabaseConnector();
        }

        @Override
        public void saveEquipment(int equipmentID, String equipmentName) {
            try (Connection con = databaseConnector.getConnection()) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO Equipment VALUES(?,?);");
                ps.setInt(1, equipmentID);
                ps.setString(2, equipmentName);

                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("cannot insert record (saveEquipment) " + e.getMessage());
            }
        }


    public void deleteEquipment(int equipmentID) {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Equipment WHERE equipmentID = ?");
            ps.setInt(1, (equipmentID));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}