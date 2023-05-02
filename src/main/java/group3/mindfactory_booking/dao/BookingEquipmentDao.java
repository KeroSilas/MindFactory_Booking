package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.Equipment;

import java.util.List;

public interface BookingEquipmentDao {

    public void addToBookingEquipment(int bookingID, int equipmentID);

    public void removeFromBookingEquipment(Equipment equipment);

    List<Equipment> getAllEquipmentOnBookingEquipment(Booking booking);
}


