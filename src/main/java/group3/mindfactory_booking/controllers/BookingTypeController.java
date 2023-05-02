package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.Booking;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class BookingTypeController {

    private Booking booking;

    @FXML
    private MFXButton åbenSkoleBtn, organisationBtn, lærerBtn;

    @FXML
    private void handleÅbenSkole() {
        booking.setBookingType("Åben Skole");
        åbenSkoleBtn.setStyle("-fx-background-color:  #94c83d; -fx-text-fill: #ffffff");
        organisationBtn.setStyle("-fx-background-color:  #ffffff; -fx-text-fill: #000000");
        lærerBtn.setStyle("-fx-background-color:  #ffffff; -fx-text-fill: #000000");
    }

    @FXML
    private void handleOrganisation() {
        booking.setBookingType("Organisation");
        organisationBtn.setStyle("-fx-background-color:  #94c83d; -fx-text-fill: #ffffff");
        åbenSkoleBtn.setStyle("-fx-background-color:  #ffffff; -fx-text-fill: #000000");
        lærerBtn.setStyle("-fx-background-color:  #ffffff; -fx-text-fill: #000000");
    }

    @FXML
    private void handleLærer() {
        booking.setBookingType("Lærer");
        lærerBtn.setStyle("-fx-background-color:  #94c83d; -fx-text-fill: #ffffff");
        åbenSkoleBtn.setStyle("-fx-background-color:  #ffffff; -fx-text-fill: #000000");
        organisationBtn.setStyle("-fx-background-color:  #ffffff; -fx-text-fill: #000000");
    }

    public void initialize() {
        booking = Booking.getInstance();

    }
}
