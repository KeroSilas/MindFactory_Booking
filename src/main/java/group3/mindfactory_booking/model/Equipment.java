package group3.mindfactory_booking.model;

public class Equipment {
    private int equipmentID;
    private String equipmentName;

    public Equipment(int equipmentID, String equipmentName) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}
