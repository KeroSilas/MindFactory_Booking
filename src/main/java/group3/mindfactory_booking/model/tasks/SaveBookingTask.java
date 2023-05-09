package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.*;
import group3.mindfactory_booking.model.singleton.Booking;
import javafx.concurrent.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                randomNum = (int)(Math.random() * (99999999 - 10000000 + 1)) + 10000000;
            } while (bookingIDs.contains(randomNum));

            booking.setBookingID(randomNum);
            booking.setBookingDateTime(LocalDateTime.now());
            bookingDao.saveBooking(booking);
        }
        catch (RuntimeException e) {
            success = false;
        }

        return success;
    }
}

