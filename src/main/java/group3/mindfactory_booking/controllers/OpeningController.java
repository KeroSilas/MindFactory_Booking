package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class OpeningController {

    @FXML
    private BorderPane openingBP, navigationBP;

    @FXML
    void handleAnnullerBooking() {

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
