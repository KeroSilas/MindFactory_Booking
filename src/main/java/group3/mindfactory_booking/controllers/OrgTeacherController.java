package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.Activity;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.Equipment;
import group3.mindfactory_booking.model.tasks.FetchActivitiesTask;
import group3.mindfactory_booking.model.tasks.FetchEquipmentTask;
import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class OrgTeacherController {

    private Booking booking;

    @FXML
    private MFXComboBox<Activity> aktivitetCB;

    @FXML
    private MFXRadioButton annesofieRB;

    @FXML
    private ToggleGroup assistanceType;

    @FXML
    private MFXRadioButton læringsRB;

    @FXML
    private MFXRadioButton nejRB;

    @FXML
    private MFXButton næsteBtn;

    @FXML
    private MFXButton tilbageBtn;

    @FXML
    private MFXComboBox<Equipment> udstyrCB;

    @FXML
    private MFXListView<Equipment> udstyrLV;

    @FXML
    void handleNæste() {
        if (isInputValid()) {
            importToBooking();
            nextPage();
        } else {
            System.out.println("Input is not valid");
        }
    }

    @FXML
    void handleTilbage() {
        previousPage();
        booking.setBookingType(null);
    }

    @FXML
    void handleTilføj() {
        udstyrLV.getItems().add(udstyrCB.getSelectionModel().getSelectedItem());
    }

    @FXML
    void handleSlet() {
        List<Equipment> equipmentList = udstyrLV.getSelectionModel().getSelectedValues();
        for (Equipment equipment : equipmentList) {
            udstyrLV.getItems().remove(equipment);
        }
    }

    public void initialize() {
        booking = Booking.getInstance();

        FetchEquipmentTask fetchEquipmentTask = new FetchEquipmentTask();
        fetchEquipmentTask.setOnSucceeded(e -> {
            udstyrCB.getItems().addAll(fetchEquipmentTask.getValue());
        });
        new Thread(fetchEquipmentTask).start();

        FetchActivitiesTask fetchActivitiesTask = new FetchActivitiesTask();
        fetchActivitiesTask.setOnSucceeded(e -> {
            aktivitetCB.getItems().addAll(fetchActivitiesTask.getValue());
        });
        new Thread(fetchActivitiesTask).start();
    }

    private void importToBooking() {
        booking.setActivity(aktivitetCB.getSelectionModel().getSelectedItem());
        booking.setEquipmentList(udstyrLV.getItems());
        if (læringsRB.isSelected()) {
            booking.setAssistance("Læring konsulent");
        } else if (annesofieRB.isSelected()) {
            booking.setAssistance("Anne-Sofie");
        } else {
            booking.setAssistance("Ingen assistance");
        }
    }

    private boolean isInputValid() {
        return true; // Redundant method since the contents of this page are all optional
    }

    private void previousPage() {
        Stage stage;
        Parent root;

        stage = (Stage) tilbageBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("bookingType-1-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void nextPage() {
        Stage stage;
        Parent root;

        stage = (Stage) næsteBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("informationOgDato-3-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

