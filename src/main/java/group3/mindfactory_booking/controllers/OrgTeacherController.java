package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.singleton.Booking;
import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class OrgTeacherController {

    private Booking booking;

    @FXML private MFXComboBox<String> aktivitetCB;
    @FXML private MFXComboBox<String> udstyrCB;
    @FXML private MFXRadioButton annesofieRB, læringsRB;
    @FXML private MFXButton næsteBtn, tilbageBtn;
    @FXML private MFXListView<String> udstyrLV;

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
        List<String> equipmentList = udstyrLV.getSelectionModel().getSelectedValues();
        for (String equipment : equipmentList) {
            udstyrLV.getItems().remove(equipment);
        }
    }

    public void initialize() {
        booking = Booking.getInstance();

        udstyrCB.getItems().addAll("Robotter", "Sakse");
        aktivitetCB.getItems().addAll("Ingen", "Kreativt Spark", "IdéGeneratoren", "Team-event: Kreativ Tech");
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
        boolean success = true;

        if (aktivitetCB.getSelectionModel().getSelectedItem()  == null) {
            aktivitetCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            aktivitetCB.setStyle("-fx-border-color: lightgrey");
        }

        return success;
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
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("information-3-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

