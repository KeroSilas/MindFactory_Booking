package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.BookingTimesDao;
import group3.mindfactory_booking.dao.BookingTimesDaoImpl;
import group3.mindfactory_booking.model.BookingTime;
import javafx.concurrent.Task;

import java.util.List;

public class ReceiveTimesTask extends Task<List<BookingTime>> {

    private final BookingTimesDao bookingTimesDao;

    public ReceiveTimesTask() {
        bookingTimesDao = new BookingTimesDaoImpl();
    }

    @Override
    public List<BookingTime> call() {
        while (!isCancelled()) { // Keep running until the task is cancelled
            updateValue(bookingTimesDao.getBookingTimeList());
            System.out.println("Times updated");
            try {
                Thread.sleep(10000L); // Sleep for the specified delay
            } catch (InterruptedException e) {
                if (isCancelled()) {
                    break;
                }
            }
        }
        return bookingTimesDao.getBookingTimeList();
    }
}
