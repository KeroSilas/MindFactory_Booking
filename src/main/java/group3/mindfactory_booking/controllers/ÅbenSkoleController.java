package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.singleton.Booking;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ÅbenSkoleController {

    private Booking booking;

    @FXML private MFXTextField afgangTF, ankomstTF;
    @FXML private MFXComboBox<String> forløbCB;
    @FXML private MFXComboBox<String> transportCB;
    @FXML private MFXButton næsteBtn, tilbageBtn;

    @FXML
    void handleNæste() {
        if (isInputValid()) {
            importToBooking();
            nextPage();
        } else {
            System.out.println("Input is not valid");
        }
    }

    @FXML
    void handleTilbage() {
        previousPage();
        booking.setBookingType(null);
    }

    public void initialize() {
        booking = Booking.getInstance();

        forløbCB.getItems().addAll("Idéfabrikken", "Digital fabrikken med laserskærer", "Robot på job", "Robotten ryder op", "Naturisme ved Vadehavet", "Skab sikkerhed i Vadehavet");
        transportCB.getItems().addAll("Lejet bus", "Offentlig transport");
    }

    private void importToBooking() {
        booking.setTransportDeparture(afgangTF.getText());
        booking.setTransportArrival(ankomstTF.getText());
        booking.setTransportType(transportCB.getSelectionModel().getSelectedItem());
        booking.setÅbenSkoleForløb(forløbCB.getSelectionModel().getSelectedItem());
    }

    private boolean isInputValid() {
        boolean success = true;

        if (afgangTF.getText().isEmpty()) {
            afgangTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            afgangTF.setStyle("-fx-border-color: lightgrey");
        }

        if (ankomstTF.getText().isEmpty()) {
            ankomstTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            ankomstTF.setStyle("-fx-border-color: lightgrey");
        }

        if (transportCB.getSelectionModel().getSelectedItem() == null) {
            transportCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            transportCB.setStyle("-fx-border-color: lightgrey");
        }

        if (forløbCB.getSelectionModel().getSelectedItem() == null) {
            forløbCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            forløbCB.setStyle("-fx-border-color: lightgrey");
        }

        return success;
    }

    private void previousPage() {
        Stage stage;
        Parent root;

        stage = (Stage) tilbageBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("bookingType-1-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void nextPage() {
        Stage stage;
        Parent root;

        stage = (Stage) næsteBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("information-3-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

