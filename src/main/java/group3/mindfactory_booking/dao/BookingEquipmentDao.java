package group3.mindfactory_booking.dao;

import java.util.List;

public interface BookingEquipmentDao {
    void saveEquipmentList(List<String> equipmentList, int bookingID);
}


