package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.dao.ForløbDao;
import group3.mindfactory_booking.dao.ForløbDaoImpl;
import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.Forløb;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class ÅbenSkoleController {

    private ForløbDao forløbDao;
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
        forløbDao = new ForløbDaoImpl();

        busRB.setUserData("Bus");
        offentligRB.setUserData("Offentlig transport");

        // Checks if text has changed, if it has, it updates the booking object
        ankomstTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setTransportArrival(newValue);
        });

        afgangTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setTransportDeparture(newValue);
        });

        forløbCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.getÅbenSkoleForløb().setForløbID(newValue.getForløbID());
        });

        transportType.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setTransportType(newValue.getUserData().toString());
        });

        forløbCB.getItems().addAll(forløbDao.getAllForløb());

    }

}

