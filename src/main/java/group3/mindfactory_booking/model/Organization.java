package group3.mindfactory_booking.model;

public class Organization {
    int organizationID;
    String organizationName;

    public Organization(int organizationID, String organizationName) {
        this.organizationID = organizationID;
        this.organizationName = organizationName;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
