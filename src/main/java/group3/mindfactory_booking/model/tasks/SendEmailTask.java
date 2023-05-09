package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.model.BookingTime;
import group3.mindfactory_booking.services.SendEmail;
import group3.mindfactory_booking.model.WeekEndHolidayChecker;
import group3.mindfactory_booking.model.singleton.Booking;
import javafx.concurrent.Task;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SendEmailTask extends Task<Boolean> {

    private final Booking booking;

    private final SendEmail sendEmail;
    private final WeekEndHolidayChecker checker;

    public SendEmailTask() {
        booking = Booking.getInstance();
        sendEmail = new SendEmail();
        checker = new WeekEndHolidayChecker();
    }

    @Override
    public Boolean call() {
        boolean success = true;
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<BookingTime> bookingTimeList = booking.getBookingTimesList();
        boolean isSpecial = false;
        for (BookingTime bookingTime : bookingTimeList) {
            if (checker.isWeekendOrHoliday(bookingTime.getDate()) || bookingTime.getEndTime().isAfter(LocalTime.of(18,0)))
                isSpecial = true;
        }

        if (isSpecial) {
            executorService.submit(() -> sendEmail.sendEmail(
                    booking.getEmail(),
                    "Du bede venligst tjekke administrations programmet.",
                    booking.getAfdeling() + " har lige booket en tid uden for normal åbningstid. Booking ID: " + booking.getBookingID(),
                    false)
            );
        }

        executorService.submit(() -> sendEmail.sendEmail(
                booking.getEmail(),
                String.valueOf(booking.getBookingID()),
                "Her er din bookingkode til Mindfactory by ECCO",
                false)
        );

        return success;
    }
}
