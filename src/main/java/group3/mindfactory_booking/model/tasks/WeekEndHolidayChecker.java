package group3.mindfactory_booking.model.tasks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Set;
// Adapted from https://simplesolution.dev/java-check-if-date-is-week-day-or-weekend-day/
// https://www.tabnine.com/code/java/methods/de.jollyday.Holiday/%3Cinit%3E
public class WeekEndHolidayChecker {

   /* private final BookingDao bookingDao;
    private final WeekEndHolidayChecker weekEndHolidayChecker;

    public WeekEndHolidayCheckerTask() {
        bookingDao = new BookingDaoImpl();
        weekEndHolidayChecker = new WeekEndHolidayChecker();
    }*/
    final Set<LocalDate> holidays = Set.of(
            LocalDate.of(2023, 5, 18),
            LocalDate.of(2023, 5, 28),
            LocalDate.of(2023, 5, 29),
            LocalDate.of(2023, 6, 5),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 26),
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 3, 28),
            LocalDate.of(2024, 3, 29),
            LocalDate.of(2024, 3, 31),
            LocalDate.of(2024, 4, 1),
            LocalDate.of(2024, 4, 26),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 5, 19),
            LocalDate.of(2024, 5, 20),
            LocalDate.of(2024, 6, 5),
            LocalDate.of(2024, 12, 25),
            LocalDate.of(2024, 12, 26),
            LocalDate.of(2025, 1, 1),
            LocalDate.of(2025, 4, 17),
            LocalDate.of(2025, 4, 18),
            LocalDate.of(2025, 4, 20),
            LocalDate.of(2025, 4, 21),
            LocalDate.of(2025, 5, 16),
            LocalDate.of(2025, 5, 29),
            LocalDate.of(2025, 6, 5),
            LocalDate.of(2025, 6, 8),
            LocalDate.of(2025, 6, 9),
            LocalDate.of(2025, 12, 25),
            LocalDate.of(2025, 12, 26));


    public boolean isWeekendOrHoliday(LocalDate localDate) {

        DayOfWeek startDate = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));
        if(DayOfWeek.SUNDAY == startDate || DayOfWeek.SATURDAY == startDate){
            return true;
        }
        else {
            return holidays.contains(startDate);
        }
    }
}

