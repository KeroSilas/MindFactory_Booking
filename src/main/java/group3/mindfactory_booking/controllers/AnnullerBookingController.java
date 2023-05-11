package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.tasks.DeleteBookingTask;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AnnullerBookingController {

    @FXML private Label statusLabel;
    @FXML private MFXProgressSpinner progressSpinner;
    @FXML private MFXTextField bookingNummerTF;
    @FXML private MFXButton afbrydBtn;

    @FXML
    void handleAnnuller() {
        Stage stage = (Stage) bookingNummerTF.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleAfbryd() {
        // Sets the statusLabel to empty, shows the progressSpinner and disables the afbrydBtn
        statusLabel.setText("");
        progressSpinner.setVisible(true);
        afbrydBtn.setDisable(true);
        afbrydBtn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;");

        // Starts a new thread to delete the booking
        // If the booking is deleted, the statusLabel will be set to "Booking slettet" and the text color will be green
        // If the booking is not found, the statusLabel will be set to "Booking ikke fundet" and the text color will be red
        // Once the thread is done, the progressSpinner will be hidden and the afbrydBtn will be enabled again
        DeleteBookingTask deleteBookingTask = new DeleteBookingTask(Integer.parseInt(bookingNummerTF.getText()));
        deleteBookingTask.setOnSucceeded(e -> {
            if (deleteBookingTask.getValue()) {
                statusLabel.setText("Booking slettet");
                statusLabel.setStyle("-fx-text-fill: green");
                bookingNummerTF.clear();
            } else {
                statusLabel.setText("Booking ikke fundet");
                statusLabel.setStyle("-fx-text-fill: red");
            }
            progressSpinner.setVisible(false);
            afbrydBtn.setDisable(false);
            afbrydBtn.setStyle("-fx-background-color: #BD2323FF; -fx-text-fill: #ffffff;");
        });
        Thread thread = new Thread(deleteBookingTask);
        thread.setDaemon(true);
        thread.start();
    }

    public void initialize() {
    }

}