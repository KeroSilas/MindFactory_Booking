package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.Activity;
import group3.mindfactory_booking.model.Catering;
import group3.mindfactory_booking.model.Organization;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.tasks.GetActivitiesTask;
import group3.mindfactory_booking.model.tasks.GetCateringTask;
import group3.mindfactory_booking.model.tasks.GetOrganisationsTask;
import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class VirksomhedController {

    private Booking booking;

    @FXML private MFXComboBox<String> udstyrCB;
    @FXML private MFXComboBox<Organization> virksomhedCB;
    @FXML private MFXComboBox<Catering> forplejningCB;
    @FXML private MFXComboBox<Activity> aktivitetCB;
    @FXML private MFXTextField afdelingTF, stillingTF, deltagereTF;
    @FXML private MFXRadioButton annesofieRB, læringsRB;
    @FXML private MFXButton næsteBtn, tilbageBtn, tilføjBtn, sletBtn;
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

        udstyrCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tilføjBtn.setDisable(newValue == null);
        });

        udstyrLV.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            sletBtn.setDisable(newValue == null);
        });

        GetOrganisationsTask getOrganisationsTask = new GetOrganisationsTask();
        getOrganisationsTask.setOnSucceeded(e -> {
            List<Organization> organisations = getOrganisationsTask.getValue();
            List<Organization> virksomheder = organisations.subList(0, 4);
            virksomhedCB.getItems().addAll(virksomheder);
        });
        Thread thread = new Thread(getOrganisationsTask);
        thread.start();

        GetActivitiesTask getActivitiesTask = new GetActivitiesTask();
        getActivitiesTask.setOnSucceeded(e -> {
            List<Activity> activities = getActivitiesTask.getValue();
            aktivitetCB.getItems().addAll(activities);
        });
        Thread thread2 = new Thread(getActivitiesTask);
        thread2.start();

        GetCateringTask getCateringTask = new GetCateringTask();
        getCateringTask.setOnSucceeded(e -> {
            List<Catering> catering = getCateringTask.getValue();
            forplejningCB.getItems().addAll(catering);
        });
        Thread thread3 = new Thread(getCateringTask);
        thread3.start();

        udstyrCB.getItems().addAll("Robotter", "Sakse");
    }

    private void importToBooking() {
        booking.getCustomer().setDepartment(afdelingTF.getText());
        booking.getCustomer().setPosition(stillingTF.getText());
        booking.setOrganization(virksomhedCB.getSelectionModel().getSelectedItem());
        booking.setActivity(aktivitetCB.getSelectionModel().getSelectedItem());
        booking.setEquipmentList(udstyrLV.getItems());
        booking.getOrganization().setParticipants(Integer.parseInt(deltagereTF.getText()));
        booking.setCatering(forplejningCB.getSelectionModel().getSelectedItem());
        if (læringsRB.isSelected()) {
            booking.getOrganization().setAssistance("Læring konsulent");
        } else if (annesofieRB.isSelected()) {
            booking.getOrganization().setAssistance("Anne-Sofie Dideriksen");
        } else {
            booking.getOrganization().setAssistance("Ingen");
        }
    }

    private boolean isInputValid() {
        boolean success = true;

        if (virksomhedCB.getSelectionModel().getSelectedItem() == null) {
            virksomhedCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            virksomhedCB.setStyle("-fx-border-color: lightgrey");
        }

        if (afdelingTF.getText().isEmpty()) {
            afdelingTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            afdelingTF.setStyle("-fx-border-color: lightgrey");
        }

        if (stillingTF.getText().isEmpty()) {
            stillingTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            stillingTF.setStyle("-fx-border-color: lightgrey");
        }

        if (aktivitetCB.getSelectionModel().getSelectedItem()  == null) {
            aktivitetCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            aktivitetCB.setStyle("-fx-border-color: lightgrey");
        }

        // https://stackoverflow.com/questions/273141/regex-for-numbers-only
        if (deltagereTF.getText().isEmpty() || !deltagereTF.getText().matches("^[0-9]+$") || Integer.parseInt(deltagereTF.getText()) > 35) {
            deltagereTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            deltagereTF.setStyle("-fx-border-color: lightgrey");
        }

        if (forplejningCB.getSelectionModel().getSelectedItem() == null) {
            forplejningCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            forplejningCB.setStyle("-fx-border-color: lightgrey");
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

