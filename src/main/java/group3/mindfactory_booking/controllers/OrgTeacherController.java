package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.Equipment;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class OrgTeacherController {

    @FXML
    private MFXComboBox<String> aktivitetCB;

    @FXML
    private MFXRadioButton annesofieRB;

    @FXML
    private ToggleGroup assistanceType;

    @FXML
    private MFXRadioButton læringsRB;

    @FXML
    private MFXComboBox<Equipment> udstyrCB;

    @FXML
    private MFXListView<Equipment> udstyrLV;

    @FXML
    void handleTilføj() {

    }

}

