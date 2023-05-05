package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.BookingDao;
import group3.mindfactory_booking.dao.BookingDaoImpl;
import group3.mindfactory_booking.model.singleton.Booking;
import javafx.concurrent.Task;

import java.time.LocalDateTime;
import java.util.List;

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
            List<Integer> bookingIDs = bookingDao.getAllBookingID();
            int randomNum;
            do {
                randomNum = (int) (Math.random()*100_000_000);
            } while (bookingIDs.contains(randomNum));
            booking.setBookingID(randomNum);
            booking.setBookingDateTime(LocalDateTime.now());
            bookingDao.saveBooking(booking);
            booking.clearBooking(); // Clear the booking after it has been saved to ensure that no information is carried over to the next booking
        } catch (RuntimeException e) {
            success = false;
        }

        return success;
    }
}
