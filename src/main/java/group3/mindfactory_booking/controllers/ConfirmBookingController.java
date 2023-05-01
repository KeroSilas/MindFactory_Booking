package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConfirmBookingController {

    private Booking booking;

    @FXML
    private Label afgangLabel, ankomstLabel, assistanceLabel, deltagereLabel, efternavnLabel,
            emailLabel, fornavnLabel, forplejningLabel, fraLabel, orgLabel, slutDatoLabel,
            specielUdstyrLabel, startDatoLabel, stillingLabel, telefonLabel, tilLabel, transportLabel;

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
        forplejningLabel.setText("?");
        orgLabel.setText("?");
        specielUdstyrLabel.setText("?");
        stillingLabel.setText("?");
        slutDatoLabel.setText("?");
        startDatoLabel.setText("?");
        fraLabel.setText("?");
        tilLabel.setText("?");
    }

}
