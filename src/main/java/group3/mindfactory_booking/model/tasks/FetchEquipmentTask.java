package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.EquipmentDao;
import group3.mindfactory_booking.dao.EquipmentDaoImpl;
import group3.mindfactory_booking.model.Equipment;
import javafx.concurrent.Task;

import java.util.List;


public class FetchEquipmentTask extends Task<List<Equipment>> {

    private final EquipmentDao equipmentDao;

    public FetchEquipmentTask() {
        equipmentDao = new EquipmentDaoImpl();
    }

    @Override
    public List<Equipment> call() {
        return equipmentDao.getAllEquipment();
    }
}
