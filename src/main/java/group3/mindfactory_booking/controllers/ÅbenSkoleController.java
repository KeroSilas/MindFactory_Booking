package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.Forløb;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class ÅbenSkoleController {

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

}

