package group3.mindfactory_booking.dao;

public class Catering {
    private int packageID;
    private String packageName;

    public Catering(int packageID, String packageName) {
        this.packageID = packageID;
        this.packageName = packageName;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
