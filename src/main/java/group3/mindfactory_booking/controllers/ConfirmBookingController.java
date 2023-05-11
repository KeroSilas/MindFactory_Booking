package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.singleton.Booking;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ConfirmBookingController {

    private Booking booking;

    @FXML private MFXButton afslutBtn;
    @FXML private Label afdelingLabel, afgangLabel, ankomstLabel, assistanceLabel, deltagereLabel, efternavnLabel, emailLabel,
            fornavnLabel, telefonLabel, transportLabel, stillingLabel, organisationLabel, forløbLabel, tidLabel,
            aktivitetLabel, udstyrLabel, forplejningLabel;

    @FXML
    void handleAfslut() {
        booking.clearBooking(); // Clear the booking after it has been saved to ensure that no information is carried over to the next booking
        goToHome();
    }

    public void initialize() {
        booking = Booking.getInstance();

        afgangLabel.setText(booking.getTransportDeparture());
        ankomstLabel.setText(booking.getTransportArrival());
        assistanceLabel.setText(booking.getAssistance());
        deltagereLabel.setText(String.valueOf(booking.getParticipants()));
        efternavnLabel.setText(booking.getLastName());
        emailLabel.setText(booking.getEmail());
        fornavnLabel.setText(booking.getFirstName());
        telefonLabel.setText(booking.getPhone());
        transportLabel.setText(booking.getTransportType());
        afdelingLabel.setText(booking.getAfdeling());
        stillingLabel.setText(booking.getPosition());
        forplejningLabel.setText(booking.getCatering());
        organisationLabel.setText(booking.getOrganization());
        forløbLabel.setText(booking.getÅbenSkoleForløb());
        aktivitetLabel.setText(booking.getActivity());
        udstyrLabel.setText(String.join(", ", booking.getEquipmentList()));
        tidLabel.setText(booking.toString());
    }

    private void goToHome() {
        Stage stage;
        Parent root;

        stage = (Stage) afslutBtn.getScene().getWindow();
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
