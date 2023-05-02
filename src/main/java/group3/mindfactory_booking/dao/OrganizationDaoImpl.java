package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Organization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDaoImpl implements OrganizationDao{
    private final DatabaseConnector databaseConnector;

    public OrganizationDaoImpl() {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public void saveOrganization(Organization organization) {
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO Organization VALUES(?,?);");
            ps.setInt(1, organization.getOrganizationID());
            ps.setString(2, organization.getOrganizationName());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record: " + e.getMessage());
        }

    }

    @Override
    public void deleteOrganization(int organizationID) {

    }


    public List<Organization> getAllOrganizations() {
        List<Organization> organizations = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Organization;");
            ResultSet rs = ps.executeQuery();

            Organization organization;
            while (rs.next()) {
                int organizationID = rs.getInt(1);
                String organizationName = rs.getString(2);

                organization = new Organization(organizationID, organizationName);
                organizations.add(organization);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllOrganizations (OrganizationDaoImpl) " + e.getMessage());
        }
        return organizations;
    }
}
