package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.Booking;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.time.LocalDateTime;

public class BookingPageController {

    private Booking booking;

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

    public void initialize() {
        booking = Booking.getInstance();

        // Checks if text has changed, if it has, it updates the booking object
        deltagereTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setParticipants(Integer.parseInt(newValue));
        });

        efternavnTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setLastName(newValue);
        });

        emailTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setEmail(newValue);
        });

        fornavnTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setFirstName(newValue);
        });

        organisationTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.getOrganization().setOrganizationName(newValue);
        });

        stillingTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setPosition(newValue);
        });

        telefonTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setPhone(newValue);
        });

        beskedTA.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setMessageToAS(newValue);
        });

        /*forplejningCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setPackageID(newValue);
        });*/

        midlertidigCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setTemporaryBooking(newValue);
        });

        startDatoDP.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setStartDate(newValue.atStartOfDay());
        });

        slutDatoDP.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setEndDate(newValue.atStartOfDay());
        });

        fraCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setStartTime(LocalDateTime.of(booking.getStartDate().toLocalDate(), LocalDateTime.parse(newValue).toLocalTime()));
        });

        tilCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setEndTime(LocalDateTime.of(booking.getEndDate().toLocalDate(), LocalDateTime.parse(newValue).toLocalTime()));
        });
    }

}
