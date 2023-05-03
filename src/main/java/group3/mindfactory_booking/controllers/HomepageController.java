package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class HomepageController {

    @FXML
    private BorderPane openingBP;

    @FXML
    void handleAfbrydBooking() {
        // Load the fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("afbrydbooking-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            // Create a new stage and scene
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            // Set the title and scene of the new window
            stage.setTitle("Afbryd booking");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(APPLICATION_MODAL);

            // Show the new window
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleBookNu() {
        Stage stage;
        Parent root;

        stage = (Stage) openingBP.getScene().getWindow();
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

    public void initialize() {
    }

}
