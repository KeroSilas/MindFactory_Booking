package group3.mindfactory_booking.model;

import java.time.LocalDate;

public class BookingEmail {
    private String name;
    private String email;
    private LocalDate startDate;

    public BookingEmail(String name, String email, LocalDate startDate) {
        this.name = name;
        this.email = email;
        this.startDate = startDate;
    }


    public String getEmail() {
        return email;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public String getName() {
        return name;
    }

}
