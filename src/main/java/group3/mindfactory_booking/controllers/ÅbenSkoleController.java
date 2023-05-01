package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.Forløb;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class ÅbenSkoleController {

    private Booking booking;

    @FXML
    private MFXTextField afgangTF;

    @FXML
    private MFXTextField ankomstTF;

    @FXML
    private MFXRadioButton busRB;

    @FXML
    private MFXComboBox<Forløb> forløbCB;

    @FXML
    private MFXRadioButton offentligRB;

    @FXML
    private ToggleGroup transportType;

    public void initialize() {
        booking = Booking.getInstance();

        // Checks if text has changed, if it has, it updates the booking object
        ankomstTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setTransportArrival(newValue);
        });

        afgangTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setTransportDeparture(newValue);
        });
    }

}

