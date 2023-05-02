package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.BookingDao;
import group3.mindfactory_booking.dao.BookingDaoImpl;
import javafx.concurrent.Task;

public class DeleteBookingTask extends Task<Boolean> {

    private final BookingDao bookingDao;

    private final int bookingID;

    public DeleteBookingTask(int bookingID) {
        this.bookingID = bookingID;
        bookingDao = new BookingDaoImpl();
    }

    @Override
    public Boolean call() {
        boolean success = true;

        try {
            bookingDao.deleteBooking(bookingID);
        } catch (RuntimeException e) {
            success = false;
        }

        return success;
    }
}
