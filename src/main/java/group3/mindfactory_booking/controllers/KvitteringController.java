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

/*
 * This class controls the 4th view in the sequence.
 * It shows all the information that the user has entered and saved to the database.
 */

public class KvitteringController {

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

        if (booking.getÅbenSkoleForløb() != null) {
            forløbLabel.setText(booking.getÅbenSkoleForløb().getÅbenSkoleForløb());
            transportLabel.setText(booking.getÅbenSkoleForløb().getTransportType());
            afgangLabel.setText(booking.getÅbenSkoleForløb().getTransportDeparture());
            ankomstLabel.setText(booking.getÅbenSkoleForløb().getTransportArrival());
        }

        if (booking.getActivity() != null) {
            aktivitetLabel.setText(booking.getActivity().getActivity());
        }

        if (booking.getCatering() != null) {
            forplejningLabel.setText(booking.getCatering().getCatering());
        }
        assistanceLabel.setText(booking.getOrganization().getAssistance());
        deltagereLabel.setText(String.valueOf(booking.getOrganization().getParticipants()));
        efternavnLabel.setText(booking.getCustomer().getLastName());
        emailLabel.setText(booking.getCustomer().getEmail());
        fornavnLabel.setText(booking.getCustomer().getFirstName());
        telefonLabel.setText(booking.getCustomer().getPhone());
        afdelingLabel.setText(booking.getCustomer().getDepartment());
        stillingLabel.setText(booking.getCustomer().getPosition());
        organisationLabel.setText(booking.getOrganization().getOrganization());
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
