package group3.mindfactory_booking.model;

import java.time.LocalDateTime;
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
    private String åbenSkoleForløb;
    private String transportType;
    private String transportArrival;
    private String transportDeparture;
    private int participants;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime bookingDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
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

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public void clearBooking() {
        this.bookingID = 0;
        this.catering = null;
        this.activity = null;
        this.organization = null;
        this.firstName = "";
        this.lastName = "";
        this.position = "";
        this.phone = "";
        this.email = "";
        this.åbenSkoleForløb = "";
        this.transportType = "";
        this.transportArrival = "";
        this.transportDeparture = "";
        this.participants = 0;
        this.startDate = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        this.endDate = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        this.bookingDate = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        this.temporaryBooking = false;
        this.assistance = "";
        this.noShow = false;
        this.messageToAS = "";
        this.personalNote = "";
        this.bookingType = "";
        this.startTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        this.endTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        this.equipmentList = null;
    }
}
