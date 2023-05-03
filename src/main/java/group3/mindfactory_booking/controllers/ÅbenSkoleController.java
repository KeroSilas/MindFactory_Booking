package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.dao.ForløbDaoImpl;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.Forløb;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ÅbenSkoleController {

    private Booking booking;
    private ForløbDaoImpl forløbDao;

    @FXML
    private MFXTextField afgangTF;

    @FXML
    private MFXTextField ankomstTF;

    @FXML
    private MFXComboBox<Forløb> forløbCB;

    @FXML
    private MFXButton næsteBtn;

    @FXML
    private MFXButton tilbageBtn;

    @FXML
    private MFXComboBox<String> transportCB;

    @FXML
    void handleNæste(ActionEvent event) {

    }

    @FXML
    void handleTilbage(ActionEvent event) {

    }

    public void initialize() {
        booking = Booking.getInstance();
        forløbDao = new ForløbDaoImpl();

        forløbCB.getItems().addAll(forløbDao.getAllForløb());
        transportCB.getItems().addAll("Lejet bus", "Offentlig transport");
    }

}

