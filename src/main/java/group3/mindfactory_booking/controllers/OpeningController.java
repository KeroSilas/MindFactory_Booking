package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class OpeningController {

    @FXML
    private BorderPane openingBP, navigationBP;

    @FXML
    void handleAnnullerBooking() {
        // Load the fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("annullerbooking-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            // Create a new stage and scene
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            // Set the title and scene of the new window
            stage.setTitle("New Window");
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
        openingBP.getScene().setRoot(navigationBP);
    }

    public void initialize() {
        FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("navigation-view.fxml"));
        try {
            navigationBP = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
