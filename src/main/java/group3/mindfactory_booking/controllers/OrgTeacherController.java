package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.Equipment;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class OrgTeacherController {

    private Booking booking;

    @FXML
    private MFXComboBox<?> aktivitetCB;

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
        udstyrLV.getItems().add(udstyrCB.getSelectionModel().getSelectedItem());
    }

    public void initialize() {
        booking = Booking.getInstance();

        annesofieRB.setUserData("Anne-Sofie");
        læringsRB.setUserData("Lærings konsulent");

        assistanceType.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setAssistance(newValue.getUserData().toString());
        });

        /*aktivitetCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setActivityID(newValue.getId());
        });*/

        udstyrLV.getItems().addListener((ListChangeListener<Equipment>) c -> {
            booking.setEquipmentList(udstyrLV.getItems());
        });
    }

}

