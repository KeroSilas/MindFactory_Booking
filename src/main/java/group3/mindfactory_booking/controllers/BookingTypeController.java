package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.singleton.Booking;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BookingTypeController {

    private Booking booking;

    @FXML
    private MFXButton åbenSkoleBtn, organisationBtn, lærerBtn, næsteBtn, tilbageBtn;

    @FXML
    public void handleNæste() {
        Stage stage;
        Parent root;

        stage = (Stage) næsteBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource(Objects.equals(booking.getBookingType(), "Åben-Skole") ? "aabenSkole-2-view.fxml" : "udstyrOgAktivitet-2-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleTilbage() {
        Stage stage;
        Parent root;

        stage = (Stage) tilbageBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("homepage-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleÅbenSkole() {
        booking.setBookingType("Åben-Skole");
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
        booking.clearBooking();
    }
}
