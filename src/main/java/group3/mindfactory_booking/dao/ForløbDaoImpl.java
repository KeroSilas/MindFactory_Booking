package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Equipment;
import group3.mindfactory_booking.model.Forløb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForløbDaoImpl implements ForløbDao{
    private final DatabaseConnector databaseConnector;
    public ForløbDaoImpl() {
        databaseConnector = DatabaseConnector.getInstance();
    }

    @Override
    public void saveForløb(Forløb forløb) {
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO ÅbenSkoleForløb VALUES(?,?);");
            ps.setInt(1, forløb.getForløbID());
            ps.setString(1, forløb.getForløbName());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record: " + e.getMessage());
        }


    }

    @Override
    public void deleteForløb(int forløbID) {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Forløb WHERE forløbID = ?");
            ps.setInt(1, forløbID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }


    public List<Forløb> getAllForløb() {
        List<Forløb> forløber = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ÅbenSkoleForløb;");
            ResultSet rs = ps.executeQuery();

            Forløb forløb;
            while (rs.next()) {
                int equipmentID = rs.getInt(1);
                String equipmentName = rs.getString(2);

                forløb = new Forløb(equipmentID, equipmentName);
                forløber.add(forløb);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllForløb (ForløbDaoImpl) " + e.getMessage());
        }
        return forløber;
    }

}
