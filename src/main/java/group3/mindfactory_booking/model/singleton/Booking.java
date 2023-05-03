package group3.mindfactory_booking.model.singleton;

import group3.mindfactory_booking.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Booking {

    private int bookingID;
    private Catering catering;
    private Activity activity;
    private Organization organization;
    private String firstName;
    private String lastName;
    private String position;
    private String phone;
    private String email;
    private String afdeling;
    private Forløb åbenSkoleForløb;
    private String transportType;
    private String transportArrival;
    private String transportDeparture;
    private int participants;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime bookingDateTime;
    private boolean isWholeDay;
    private boolean temporaryBooking;
    private String assistance;
    private boolean noShow;
    private String messageToAS;
    private String personalNote;
    private String bookingType;
    private List<Equipment> equipmentList;

 /*   private Booking(int bookingID, int packageID, int activityID, int organizationID, String firstName, String lastName, String position, String phone, String email, String åbenSkoleForløb, String transportType, String transportArrival, String transportDeparture, int participants, LocalDate startDate, LocalDate endDate, LocalDate bookingDate, boolean temporaryBooking, String assistance, boolean noShow, String messageToAS, String personalNote) {
        this.bookingID = bookingID;
        this.packageID = packageID;
        this.activityID = activityID;
        this.organizationID = organizationID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.åbenSkoleForløb = åbenSkoleForløb;
        this.transportType = transportType;
        this.transportArrival = transportArrival;
        this.transportDeparture = transportDeparture;
        this.participants = participants;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingDate = bookingDate;
        this.temporaryBooking = temporaryBooking;
        this.assistance = assistance;
        this.noShow = noShow;
        this.messageToAS = messageToAS;
        this.personalNote = personalNote;
    }*/
    private static Booking instance = null;

    private Booking() {

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
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
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

    public boolean isTemporaryBooking() {
        return temporaryBooking;
    }

    public void setTemporaryBooking(boolean temporaryBooking) {
        this.temporaryBooking = temporaryBooking;
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

    public void clearBooking() {
        this.bookingID = 0;
        this.catering = new Catering(1, ""); // Testing
        this.activity = new Activity(1, ""); // Testing
        this.organization = new Organization(1,""); // Testing
        this.firstName = "";
        this.lastName = "";
        this.position = "";
        this.phone = "";
        this.email = "";
        this.åbenSkoleForløb = new Forløb(1, "");
        this.transportType = "";
        this.transportArrival = "";
        this.transportDeparture = "";
        this.participants = 0;
        this.date = LocalDate.of(2000, 1, 1);
        this.bookingDateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        this.temporaryBooking = false;
        this.assistance = "";
        this.noShow = false;
        this.messageToAS = "";
        this.personalNote = "";
        this.bookingType = "";
        this.startTime = LocalTime.of(0, 0);
        this.endTime = LocalTime.of(0, 0);
        this.equipmentList = null;
    }

    public void printBooking() {
        System.out.println("Booking ID: " + bookingID);
        System.out.println("Catering: " + catering);
        System.out.println("Activity: " + activity.getActivityID() + " " + activity.getActivityName());
        System.out.println("Organization: " + organization.getOrganizationID() + " " + organization.getOrganizationName());
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Position: " + position);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Åben skole forløb: " + åbenSkoleForløb.getForløbID() + " " + åbenSkoleForløb.getForløbName());
        System.out.println("Transport type: " + transportType);
        System.out.println("Transport arrival: " + transportArrival);
        System.out.println("Transport departure: " + transportDeparture);
        System.out.println("Participants: " + participants);
        System.out.println("Date: " + date);
        System.out.println("Booking date: " + bookingDateTime);
        System.out.println("Temporary booking: " + temporaryBooking);
        System.out.println("Assistance: " + assistance);
        System.out.println("No show: " + noShow);
        System.out.println("Message to AS: " + messageToAS);
        System.out.println("Personal note: " + personalNote);
        System.out.println("Booking type: " + bookingType);
        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);
        System.out.println("Equipment list: " + equipmentList);
    }
}
