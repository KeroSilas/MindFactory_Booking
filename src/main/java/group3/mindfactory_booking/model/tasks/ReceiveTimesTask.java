package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.BookingDao;
import group3.mindfactory_booking.dao.BookingDaoImpl;
import group3.mindfactory_booking.model.BookingTime;
import javafx.concurrent.Task;

import java.util.List;

public class ReceiveTimesTask extends Task<List<BookingTime>> {

    private final BookingDao bookingTimesDao;

    public ReceiveTimesTask() {
        bookingTimesDao = new BookingDaoImpl();
    }

    @Override
    public List<BookingTime> call() {
        while (!isCancelled()) { // Keep running until the task is cancelled
            updateValue(bookingTimesDao.getBookingTimeList());
            try {
                Thread.sleep(5000L); // Sleep for the specified delay
            } catch (InterruptedException e) {
                if (isCancelled()) {
                    break;
                }
            }
        }
        return bookingTimesDao.getBookingTimeList();
    }
}
