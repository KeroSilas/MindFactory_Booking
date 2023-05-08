package group3.mindfactory_booking.model;

import java.time.LocalDate;

public class BookingEmail {
    private int bookingID;
    private String email;
    private LocalDate startDate;

    public BookingEmail(int bookingID, String email, LocalDate startDate) {
        this.bookingID = bookingID;
        this.email = email;
        this.startDate = startDate;
    }

    public int getBookingID() {
        return bookingID;
    }


    public String getEmail() {
        return email;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

}
