package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Organization;

import java.util.List;

public interface OrganizationDao {

    public void saveOrganization(Organization organization);

    public void deleteOrganization(int organizationID);

    List<Organization> getAllOrganizations();
}
