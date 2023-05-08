package group3.mindfactory_booking.dao;

import java.util.List;

public interface BookingEquipmentDao {

    public void addToBookingEquipment(int bookingID, int equipmentID);

    public void removeFromBookingEquipment(int equipmentID);

    public void saveEquipmentList(List<String> equipmentList, int bookingID);
}


