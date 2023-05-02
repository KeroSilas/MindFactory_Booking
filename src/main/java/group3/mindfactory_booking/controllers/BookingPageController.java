package group3.mindfactory_booking.controllers;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class BookingPageController {

    @FXML
    private Label alertLabel;

    @FXML
    private TextArea beskedTA;

    @FXML
    private MFXTextField deltagereTF;

    @FXML
    private MFXTextField efternavnTF;

    @FXML
    private MFXTextField emailTF;

    @FXML
    private MFXTextField fornavnTF;

    @FXML
    private MFXCheckbox forplejningCB;

    @FXML
    private MFXComboBox<String> fraCB;

    @FXML
    private MFXCheckbox midlertidigCB;

    @FXML
    private MFXTextField organisationTF;

    @FXML
    private MFXDatePicker slutDatoDP;

    @FXML
    private MFXDatePicker startDatoDP;

    @FXML
    private MFXTextField stillingTF;

    @FXML
    private MFXTextField telefonTF;

    @FXML
    private MFXComboBox<String> tilCB;

}
