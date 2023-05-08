package group3.mindfactory_booking.model.tasks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Set;

public class WeekEndHolidayChecker {

    public WeekEndHolidayChecker(LocalDate localDate) {

    }

    //https://java2blog.com/check-if-date-is-weekend-or-weekday-java/#:~:text=We%20can%20use%20LocalDate's%20getDayOfWeek,day%20is%20weekend%20or%20not.
    //Java2Blog (java2blog.com)
    public static boolean isWeekend(final LocalDate localDate)
    {
        DayOfWeek startDate = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));
        if(startDate != DayOfWeek.SATURDAY || startDate != DayOfWeek.SUNDAY)
        {
            return false;
        }
        return true;
    }


    // Hardcoded holidays as LocalDates put in a Set:
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

    // Gives a date to check against holiday Set:
    LocalDate startDate = LocalDate.of(2023, 5, 18);
    //boolean isHoliday = containsDate(holidays, startDate);


    public static boolean isHoliday(Set<LocalDate> holidays, LocalDate startDate) {





        holidays.contains(startDate);
        return true;
    }

}
