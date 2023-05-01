package group3.mindfactory_booking.model;

import java.time.LocalDate;

public class Booking {

    private int bookingID;
    private int packageID;
    private int activityID;
    private int organizationID;
    private String firstName;
    private String lastName;
    private String position;
    private String phone;
    private String email;
    private String åbenSkoleForløb;
    private String transportType;
    private String transportArrival;
    private String transportDeparture;
    private int participants;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate bookingDate;
    private boolean temporaryBooking;
    private String assistance;
    private boolean noShow;
    private String messageToAS;
    private String personalNote;

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

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
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

    public String getÅbenSkoleForløb() {
        return åbenSkoleForløb;
    }

    public void setÅbenSkoleForløb(String åbenSkoleForløb) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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
}
