package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.*;
import group3.mindfactory_booking.model.singleton.Booking;
import javafx.concurrent.Task;

public class SaveBookingEquipmentTask extends Task<Boolean> {

    private final Booking booking;

    private final BookingEquipmentDao bookingEquipmentDao;

    public SaveBookingEquipmentTask() {
        booking = Booking.getInstance();
        bookingEquipmentDao = new BookingEquipmentDaoImpl();
    }

    @Override
    public Boolean call() {
        boolean success = true;

        try {
            bookingEquipmentDao.saveEquipmentList(booking.getEquipmentList(), booking.getBookingID());
        }
        catch (RuntimeException e) {
            success = false;
        }

        return success;
    }
}

