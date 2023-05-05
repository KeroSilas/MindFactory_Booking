package group3.mindfactory_booking.model.singleton;

import group3.mindfactory_booking.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    private int bookingID;
    private Catering catering;
    private Activity activity;
    private Organization organization;
    private Forløb åbenSkoleForløb;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private String phone;
    private String email;
    private String assistance;
    private String transportType;
    private String transportArrival;
    private String transportDeparture;
    private int participants;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime bookingDateTime;
    private boolean isWholeDay;
    private boolean noShow;
    private String messageToAS;
    private String personalNote;
    private String bookingType;
    private List<Equipment> equipmentList;
    private List<BookingTime> bookingTimeList;

    private static Booking instance = null;

    private Booking() {
        this.activity = new Activity(4, ""); // Make sure that the ID matches the ID of "Ingen" in the database
        this.åbenSkoleForløb = new Forløb(7, ""); // Make sure that the ID matches the ID of "Ikke valgt" in the database
        this.equipmentList = new ArrayList<>();
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

    public Catering getCatering() {
        return catering;
    }

    public void setCatering(Catering catering) {
        this.catering = catering;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Forløb getÅbenSkoleForløb() {
        return åbenSkoleForløb;
    }

    public void setÅbenSkoleForløb(Forløb åbenSkoleForløb) {
        this.åbenSkoleForløb = åbenSkoleForløb;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getTransportArrival() {
        return transportArrival;
    }

    public void setTransportArrival(String transportArrival) {
        this.transportArrival = transportArrival;
    }

    public String getTransportDeparture() {
        return transportDeparture;
    }

    public void setTransportDeparture(String transportDeparture) {
        this.transportDeparture = transportDeparture;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public boolean isWholeDay() {
        return isWholeDay;
    }

    public void setWholeDay(boolean wholeDay) {
        isWholeDay = wholeDay;
    }

    public String getAfdeling() {
        return department;
    }

    public void setAfdeling(String afdeling) {
        this.department = afdeling;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public String getAssistance() {
        return assistance;
    }

    public void setAssistance(String assistance) {
        this.assistance = assistance;
    }

    public boolean isNoShow() {
        return noShow;
    }

    public void setNoShow(boolean noShow) {
        this.noShow = noShow;
    }

    public String getMessageToAS() {
        return messageToAS;
    }

    public void setMessageToAS(String messageToAS) {
        this.messageToAS = messageToAS;
    }

    public String getPersonalNote() {
        return personalNote;
    }

    public void setPersonalNote(String personalNote) {
        this.personalNote = personalNote;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<BookingTime> getBookingTimesList() {
        return bookingTimeList;
    }

    public void setBookingTimesList(List<BookingTime> bookingTimeList) {
        this.bookingTimeList = bookingTimeList;
    }

    public void clearBooking() {
        this.catering = null;
        this.activity = new Activity(4, ""); // Make sure that the ID matches the ID of "Ingen" in the database
        this.organization = null;
        this.åbenSkoleForløb = new Forløb(7, ""); // Make sure that the ID matches the ID of "Ikke valgt" in the database
        this.firstName = "";
        this.lastName = "";
        this.position = "";
        this.department = "";
        this.phone = "";
        this.email = "";
        this.assistance = "";
        this.transportType = "";
        this.transportArrival = "";
        this.transportDeparture = "";
        this.participants = 0;
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.bookingDateTime = null;
        this.isWholeDay = false;
        this.noShow = false;
        this.messageToAS = "";
        this.personalNote = "";
        this.bookingType = null;
        this.equipmentList = new ArrayList<>();
        this.bookingTimeList = new ArrayList<>();
    }
}
