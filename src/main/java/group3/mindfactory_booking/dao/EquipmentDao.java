package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Equipment;

import java.util.List;

public interface EquipmentDao {

    public void saveEquipment(Equipment equipment);

    void deleteEquipment(int equipmentID);

    List<Equipment> getAllEquipment();
}
