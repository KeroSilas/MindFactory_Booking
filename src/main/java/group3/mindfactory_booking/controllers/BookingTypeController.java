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

    @FXML private MFXButton skoleBtn, virksomhedBtn, næsteBtn, tilbageBtn;

    @FXML
    public void handleNæste() {
        if (isBookingTypeSelected()) {
            nextPage();
        } else {
            System.out.println("Input is not valid");
        }
    }

    @FXML
    public void handleTilbage() {
        previousPage();
    }

    @FXML
    private void handleSkole() {
        booking.setBookingType("Skole");
        skoleBtn.setStyle("-fx-background-color:  #94c83d; -fx-text-fill: #ffffff");
        virksomhedBtn.setStyle("-fx-background-color:  #ffffff; -fx-text-fill: #000000");
    }

    @FXML
    private void handleVirksomhed() {
        booking.setBookingType("Virksomhed");
        virksomhedBtn.setStyle("-fx-background-color:  #94c83d; -fx-text-fill: #ffffff");
        skoleBtn.setStyle("-fx-background-color:  #ffffff; -fx-text-fill: #000000");
    }

    public void initialize() {
        booking = Booking.getInstance();
    }

    private boolean isBookingTypeSelected() {
        return booking.getBookingType() != null;
    }

    private void nextPage() {
        Stage stage;
        Parent root;

        stage = (Stage) næsteBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource(Objects.equals(booking.getBookingType(), "Skole") ? "skole-2-view.fxml" : "virksomhed-2-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void previousPage() {
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
}
