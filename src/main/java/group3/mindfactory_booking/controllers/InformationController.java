package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.Catering;
import group3.mindfactory_booking.model.Organization;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class InformationController {

    private Booking booking;

    @FXML
    private MFXTextField afdelingTF;

    @FXML
    private Label alertLabel;

    @FXML
    private TextArea beskedTA;

    @FXML
    private MFXComboBox<String> datoCB;

    @FXML
    private MFXTextField deltagereTF;

    @FXML
    private MFXTextField efternavnTF;

    @FXML
    private MFXTextField emailTF;

    @FXML
    private MFXTextField fornavnTF;

    @FXML
    private MFXComboBox<Catering> forplejningCB;

    @FXML
    private MFXComboBox<String> fraCB;

    @FXML
    private MFXCheckbox midlertidigCB;

    @FXML
    private MFXButton næsteBtn;

    @FXML
    private MFXComboBox<Organization> organisationCB;

    @FXML
    private MFXTextField stillingTF;

    @FXML
    private MFXTextField telefonTF;

    @FXML
    private MFXComboBox<String> tilCB;

    @FXML
    private MFXButton tilbageBtn;

    @FXML
    void handleNæste(ActionEvent event) {

    }

    @FXML
    void handleTilbage(ActionEvent event) {

    }

    public void initialize() {
        booking = Booking.getInstance();

    }

}
