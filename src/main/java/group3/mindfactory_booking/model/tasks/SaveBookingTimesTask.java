package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.*;
import group3.mindfactory_booking.model.singleton.Booking;
import javafx.concurrent.Task;

public class SaveBookingTimesTask extends Task<Boolean> {

    private final Booking booking;

    private final BookingTimesDao bookingTimesDao;

    public SaveBookingTimesTask() {
        booking = Booking.getInstance();
        bookingTimesDao = new BookingTimesDaoImpl();
    }

    @Override
    public Boolean call() {
        boolean success = true;

        try {
            bookingTimesDao.saveBookingTimeList(booking.getBookingTimesList(), booking.getBookingID());
        }
        catch (RuntimeException e) {
            success = false;
        }

        return success;
    }
}

