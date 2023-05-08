package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.BookingDao;
import group3.mindfactory_booking.dao.BookingDaoImpl;
import group3.mindfactory_booking.model.SendEmail;
import group3.mindfactory_booking.model.singleton.Booking;
import javafx.concurrent.Task;

import java.time.LocalDateTime;
import java.util.List;

public class SaveBookingTask extends Task<Boolean> {

    private final Booking booking;

    private final BookingDao bookingDao;
    private final SendEmail sendEmail;

    public SaveBookingTask() {
        booking = Booking.getInstance();
        bookingDao = new BookingDaoImpl();
        sendEmail = new SendEmail();
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

            sendEmail.sendEmail(booking.getEmail(), randomNum);
        } catch (RuntimeException e) {
            success = false;
        }

        return success;
    }
}

