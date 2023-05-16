package group3.mindfactory_booking.model.singleton;

import group3.mindfactory_booking.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    private int bookingID;
    private Customer customer;
    private Catering catering;
    private Activity activity;
    private Organization organization;
    private Forløb åbenSkoleForløb;
    private LocalDateTime bookingDateTime;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isWholeDay;
    private boolean isNoShow;
    private boolean isEmailSent;
    private String messageToAS;
    private String personalNote;
    private List<String> equipmentList;

    private String bookingType;

    private static Booking instance = null;

    private Booking() {
        clearBooking();
    }

    public static Booking getInstance() {
        if (instance == null) {
            instance = new Booking();
        }
        return instance;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public boolean isEmailSent() {
        return isEmailSent;
    }

    public void setEmailSent(boolean isEmailSent) {
        this.isEmailSent = isEmailSent;
    }

    public String getMessageToAS() {
        return messageToAS;
    }

    public void setMessageToAS(String messageToAS) {
        this.messageToAS = messageToAS;
    }

    public List<String> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<String> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isWholeDay() {
        if (startTime.isBefore(LocalTime.of(12, 0)) && endTime.isAfter(LocalTime.of(12, 0))) {
            isWholeDay = true;
        }
        return isWholeDay;
    }

    public boolean isNoShow() {
        return isNoShow;
    }

    public Catering getCatering() {
        if (catering == null) {
            catering = new Catering();
        }
        return catering;
    }

    public void setCatering(Catering catering) {
        this.catering = catering;
    }

    public Activity getActivity() {
        if (activity == null) {
            activity = new Activity();
        }
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Organization getOrganization() {
        if (organization == null) {
            organization = new Organization();
        }
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Forløb getÅbenSkoleForløb() {
        if (åbenSkoleForløb == null) {
            åbenSkoleForløb = new Forløb();
        }
        return åbenSkoleForløb;
    }

    public void setÅbenSkoleForløb(Forløb åbenSkoleForløb) {
        this.åbenSkoleForløb = åbenSkoleForløb;
    }

    public Customer getCustomer() {
        if (customer == null) {
            customer = new Customer();
        }
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getPersonalNote() {
        return personalNote;
    }

    public void setPersonalNote (String personalNote) {
        this.personalNote = personalNote;
    }

    public void clearBooking() {
        this.customer = null;
        this.catering = null;
        this.activity = null;
        this.organization = null;
        this.åbenSkoleForløb = null;
        this.bookingDateTime = null;
        this.startDate = null;
        this.startTime = null;
        this.endTime = null;
        this.isWholeDay = false;
        this.isEmailSent = false;
        this.bookingType = null;
        this.messageToAS = null;
        this.personalNote = null;
        this.equipmentList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return startDate + " " + startTime + " - " + endTime;
    }

}
