package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.Equipment;

import java.util.List;

public interface BookingEquipmentDao {

    public void addBookingEquip(int bookingID, int equipmentID);

    public void removeFromBookingEquip(Equipment equipment);

 //   List<Equipment> getAllEquipmentOnBookingEquip(Booking booking);

}
