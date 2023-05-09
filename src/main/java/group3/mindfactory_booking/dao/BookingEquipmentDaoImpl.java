package group3.mindfactory_booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookingEquipmentDaoImpl implements BookingEquipmentDao{
    private final DatabaseConnector databaseConnector;

    public BookingEquipmentDaoImpl() {
        databaseConnector = DatabaseConnector.getInstance();
    }

    @Override
    public void saveEquipmentList(List<String> equipmentList, int bookingID) {

        try (Connection con = databaseConnector.getConnection()){
            con.setAutoCommit(false);
            String sql = "INSERT INTO BookingEquipment VALUES(?,?);";
            PreparedStatement ps = con.prepareStatement(sql);

            for (String equipment : equipmentList) {
                ps.setInt(1, bookingID);
                ps.setString(2, equipment);
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


