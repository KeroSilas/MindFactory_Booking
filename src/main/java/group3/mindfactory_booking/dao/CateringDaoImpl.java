package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Catering;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CateringDaoImpl implements CateringDao {
    private final DatabaseConnector databaseConnector;

    public CateringDaoImpl() {
        databaseConnector = new DatabaseConnector();
    }
    @Override
    public void saveCatering(Catering catering) {

            try (Connection con = databaseConnector.getConnection()){
                PreparedStatement ps = con.prepareStatement("INSERT INTO Equipment VALUES(?,?);");
                ps.setInt(1, catering.getPackageID());
                ps.setString(1, catering.getPackageName());

                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("cannot insert record: " + e.getMessage());
            }

    }

    @Override
    public void deleteCatering(int cateringID) {

    }


    public List<Catering> getAllCatering() {
        List<Catering> caterings = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Catering;");
            ResultSet rs = ps.executeQuery();

            Catering catering;
            while (rs.next()) {
                int packageID = rs.getInt(1);
                String packageName = rs.getString(2);

                catering = new Catering(packageID, packageName);
                caterings.add(catering);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllCaterings (CateringDaoImpl) " + e.getMessage());
        }
        return caterings;


    }
}
