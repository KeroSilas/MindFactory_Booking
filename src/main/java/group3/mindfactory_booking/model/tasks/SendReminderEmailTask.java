package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.BookingDao;
import group3.mindfactory_booking.dao.BookingDaoImpl;
import group3.mindfactory_booking.model.BookingEmail;
import group3.mindfactory_booking.model.SendEmail;
import group3.mindfactory_booking.model.singleton.Booking;
import javafx.concurrent.Task;

import java.util.List;

public class SendReminderEmailTask extends Task<Void> {

    private final BookingDao bookingDao;
    private final SendEmail sendEmail;

    public SendReminderEmailTask() {
        bookingDao = new BookingDaoImpl();
        sendEmail = new SendEmail();
    }

    @Override
    protected Void call() {
        while (!isCancelled()) { // Keep running until the task is cancelled
            try {
                Thread.sleep(30000L);
            } catch (InterruptedException e) {
                if (isCancelled()) {
                    break;
                }
            }
            List<BookingEmail> bookingEmailList = bookingDao.getOneWeekOutBookings();
            for (BookingEmail bookingEmail : bookingEmailList) {
                sendEmail.sendEmail(bookingEmail.getEmail(),
                        "Hej " + bookingEmail.getName() + "\n" +
                                "Du har en booking snart hos os her på denne dato: " + bookingEmail.getStartDate(), "Din kommende booking.");
            }
        }

        return null;
    }
}