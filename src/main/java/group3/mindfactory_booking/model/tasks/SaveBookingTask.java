package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.BookingDao;
import group3.mindfactory_booking.dao.BookingDaoImpl;
import group3.mindfactory_booking.model.Booking;
import javafx.concurrent.Task;

public class SaveBookingTask extends Task<Boolean> {

    private final Booking booking;

    private final BookingDao bookingDao;

    public SaveBookingTask() {
        booking = Booking.getInstance();
        bookingDao = new BookingDaoImpl();
    }

    @Override
    public Boolean call() {
        boolean success = true;

        try {
            int randomNum = (int)(Math.random() * (9 - 1 + 1)) + 1;  // 1 digit number
            if(bookingDao.checkBookingID(randomNum)){
                booking.setBookingID(randomNum);
            }
                bookingDao.saveBooking(booking);
        } catch (RuntimeException e) {
            success = false;
        }
        return success;
    }
}
