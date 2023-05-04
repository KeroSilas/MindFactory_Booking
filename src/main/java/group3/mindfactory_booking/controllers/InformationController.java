package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.Catering;
import group3.mindfactory_booking.model.Organization;
import group3.mindfactory_booking.model.tasks.FetchCateringTask;
import group3.mindfactory_booking.model.tasks.FetchOrganizationsTask;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class InformationController {

    private Booking booking;

    @FXML
    private MFXProgressSpinner progressSpinner;

    @FXML
    private MFXTextField afdelingTF;

    @FXML
    private Label alertLabel;

    @FXML
    private TextArea beskedTA;

    @FXML
    private MFXComboBox<LocalDate> datoCB;

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
    private MFXComboBox<LocalTime> fraCB;

    @FXML
    private MFXButton næsteBtn;

    @FXML
    private MFXComboBox<Organization> organisationCB;

    @FXML
    private MFXTextField stillingTF;

    @FXML
    private MFXTextField telefonTF;

    @FXML
    private MFXComboBox<LocalTime> tilCB;

    @FXML
    private MFXButton tilbageBtn;

    @FXML
    void handleNæste() {
        if (isInputValid()) {
            progressSpinner.setVisible(true);
            næsteBtn.setDisable(true);

            importToBooking();
            nextPage();
        } else {
            System.out.println("Input is not valid");
        }
    }

    @FXML
    void handleTilbage() {
        previousPage();
    }

    public void initialize() {
        booking = Booking.getInstance();

        FetchOrganizationsTask fetchOrganizationsTask = new FetchOrganizationsTask();
        fetchOrganizationsTask.setOnSucceeded(e -> {
            organisationCB.getItems().addAll(fetchOrganizationsTask.getValue());
        });
        new Thread(fetchOrganizationsTask).start();

        FetchCateringTask fetchCateringTask = new FetchCateringTask();
        fetchCateringTask.setOnSucceeded(e -> {
            forplejningCB.getItems().addAll(fetchCateringTask.getValue());
        });
        new Thread(fetchCateringTask).start();
    }

    private void importToBooking() {
        booking.setAfdeling(afdelingTF.getText());
        booking.setMessageToAS(beskedTA.getText());
        booking.setDate(datoCB.getValue());
        booking.setParticipants(Integer.parseInt(deltagereTF.getText()));
        booking.setLastName(efternavnTF.getText());
        booking.setEmail(emailTF.getText());
        booking.setFirstName(fornavnTF.getText());
        booking.setCatering(forplejningCB.getValue());
        booking.setStartTime(fraCB.getValue());
        booking.setOrganization(organisationCB.getValue());
        booking.setPosition(stillingTF.getText());
        booking.setPhone(telefonTF.getText());
        booking.setEndTime(tilCB.getValue());
    }

    private boolean isInputValid() {
        boolean success = true;

        if (afdelingTF.getText().isEmpty()) {
            afdelingTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            afdelingTF.setStyle("-fx-border-color: lightgrey");
        }

        if (datoCB.getSelectionModel().getSelectedItem() == null) {
            datoCB.setStyle("-fx-border-color: red");
            //success = false;
        } else {
            datoCB.setStyle("-fx-border-color: lightgrey");
        }

        // https://stackoverflow.com/questions/273141/regex-for-numbers-only
        if (deltagereTF.getText().isEmpty() || !deltagereTF.getText().matches("^[0-9]+$")) {
            deltagereTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            deltagereTF.setStyle("-fx-border-color: lightgrey");
        }

        if (fornavnTF.getText().isEmpty()) {
            fornavnTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            fornavnTF.setStyle("-fx-border-color: lightgrey");
        }

        if (efternavnTF.getText().isEmpty()) {
            efternavnTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            efternavnTF.setStyle("-fx-border-color: lightgrey");
        }

        // https://stackoverflow.com/questions/60282362/regex-pattern-for-email
        if (emailTF.getText().isEmpty() || !emailTF.getText().matches("^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$")) {
            emailTF.setStyle("-fx-border-color: red");
            success = false;
        } else if (booking.getBookingType().equals("Lærer") && !emailTF.getText().substring(emailTF.getText().length() - 11).equals("skoletdr.dk")) {
            emailTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            emailTF.setStyle("-fx-border-color: lightgrey");
        }

        if (forplejningCB.getSelectionModel().getSelectedItem() == null) {
            forplejningCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            forplejningCB.setStyle("-fx-border-color: lightgrey");
        }

        if (fraCB.getSelectionModel().getSelectedItem() == null) {
            fraCB.setStyle("-fx-border-color: red");
            //success = false;
        } else {
            fraCB.setStyle("-fx-border-color: lightgrey");
        }

        if (organisationCB.getSelectionModel().getSelectedItem() == null) {
            organisationCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            organisationCB.setStyle("-fx-border-color: lightgrey");
        }

        if (stillingTF.getText().isEmpty()) {
            stillingTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            stillingTF.setStyle("-fx-border-color: lightgrey");
        }

        if (telefonTF.getText().isEmpty()) {
            telefonTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            telefonTF.setStyle("-fx-border-color: lightgrey");
        }

        if (tilCB.getSelectionModel().getSelectedItem() == null) {
            tilCB.setStyle("-fx-border-color: red");
            //success = false;
        } else {
            tilCB.setStyle("-fx-border-color: lightgrey");
        }

        return success;
    }

    private void nextPage() {
        Stage stage;
        Parent root;

        stage = (Stage) næsteBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("bekræftBooking-4-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void previousPage() {
        Stage stage;
        Parent root;

        stage = (Stage) tilbageBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource(Objects.equals(booking.getBookingType(), "Åben-Skole") ? "aabenSkole-2-view.fxml" : "udstyrOgAktivitet-2-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
