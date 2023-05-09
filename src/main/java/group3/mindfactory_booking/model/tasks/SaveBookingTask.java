package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.*;
import group3.mindfactory_booking.model.BookingTime;
import group3.mindfactory_booking.model.SendEmail;
import group3.mindfactory_booking.model.singleton.Booking;
import javafx.concurrent.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SaveBookingTask extends Task<Boolean> {

    private final Booking booking;

    private final BookingDao bookingDao;
    private final BookingEquipmentDao bookingEquipmentDao;
    private final BookingTimesDao bookingTimesDao;
    private final SendEmail sendEmail;
    private final WeekEndHolidayChecker checker;

    public SaveBookingTask() {
        booking = Booking.getInstance();
        bookingDao = new BookingDaoImpl();
        bookingEquipmentDao = new BookingEquipmentDaoImpl();
        bookingTimesDao = new BookingTimesDaoImpl();
        sendEmail = new SendEmail();
        checker = new WeekEndHolidayChecker();
    }

    @Override
    public Boolean call() {
        boolean success = true;
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            List<Integer> bookingIDs = bookingDao.getAllBookingID();
            int randomNum;
            do {
                randomNum = (int)(Math.random() * (99999999 - 10000000 + 1)) + 10000000;
            } while (bookingIDs.contains(randomNum));


            List<BookingTime> bookingTimeList = booking.getBookingTimesList();
            boolean isSpecial = false;
            for (BookingTime bookingTime : bookingTimeList) {
                isSpecial = checker.isWeekendOrHoliday(bookingTime.getDate());
            }
            if (isSpecial) {
                sendEmail.sendEmail(booking.getEmail(), "test", "test");
            }

            else {

                booking.setBookingID(randomNum);
            booking.setBookingDateTime(LocalDateTime.now());
            bookingDao.saveBooking(booking);

            executorService.submit(() -> bookingTimesDao.saveBookingTimeList(booking.getBookingTimesList(), booking.getBookingID()));
            executorService.submit(() -> bookingEquipmentDao.saveEquipmentList(booking.getEquipmentList(), booking.getBookingID()));
            executorService.submit(() -> sendEmail.sendEmail(booking.getEmail(),
                    String.valueOf(booking.getBookingID()),
                    "Her er din bookingkode til Mindfactory by ECCO"));
            executorService.shutdown();
        }
        }
        catch (RuntimeException e) {
            success = false;
        }

        return success;
    }
}

