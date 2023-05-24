package group3.mindfactory_booking.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeekEndHolidayCheckerTest {

    @Test
    void isWeekendTrue() {
        // Arrange
        WeekEndHolidayChecker checker = new WeekEndHolidayChecker();

        // Act
        boolean isWeekendOrHoliday = checker.isWeekendOrHoliday(LocalDate.of(2023, 5, 20));

        // Assert
        assertTrue(isWeekendOrHoliday);
    }

    @Test
    void isWeekendFalse() {
        // Arrange
        WeekEndHolidayChecker checker = new WeekEndHolidayChecker();

        // Act
        boolean isWeekendOrHoliday = checker.isWeekendOrHoliday(LocalDate.of(2023, 5, 19));

        // Assert
        assertFalse(isWeekendOrHoliday);
    }

    @Test
    void isHolidayTrue() {
        // Arrange
        WeekEndHolidayChecker checker = new WeekEndHolidayChecker();

        // Act
        boolean isWeekendOrHoliday = checker.isWeekendOrHoliday(LocalDate.of(2023, 5, 25));

        // Assert
        assertTrue(isWeekendOrHoliday);
    }

    @Test
    void isHolidayFalse() {
        // Arrange
        WeekEndHolidayChecker checker = new WeekEndHolidayChecker();

        // Act + Assert
        assertFalse(checker.isWeekendOrHoliday(LocalDate.of(2023, 6, 2)));
    }
}