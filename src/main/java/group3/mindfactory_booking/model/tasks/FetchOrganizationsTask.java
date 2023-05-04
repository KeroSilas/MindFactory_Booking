package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.OrganizationDao;
import group3.mindfactory_booking.dao.OrganizationDaoImpl;
import group3.mindfactory_booking.model.Organization;
import javafx.concurrent.Task;

import java.util.List;


public class FetchOrganizationsTask extends Task<List<Organization>> {

    private final OrganizationDao organizationDao;

    public FetchOrganizationsTask() {
        organizationDao = new OrganizationDaoImpl();
    }

    @Override
    public List<Organization> call() {
        return organizationDao.getAllOrganizations();
    }
}
