package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.Equipment;

import java.util.List;

public interface BookingEquipmentDao {

    public void addToBookingEquipment(int bookingID, int equipmentID);

    public void removeFromBookingEquipment(int equipmentID);

    List<Equipment> getAllEquipmentOnBookingEquipment(Booking booking);
    public void saveEquipmentList(List<Equipment> equipmentList, int bookingID);
}


