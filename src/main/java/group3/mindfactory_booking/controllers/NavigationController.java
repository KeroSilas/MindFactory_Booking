package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.Booking;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.LinkedList;

public class NavigationController {

    @FXML
    private StackPane stackPane;

    private LinkedList<BorderPane> views;
    private int currentView;

    private Booking booking;

    @FXML
    public void handleForrige() {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(views.get(++currentView));
    }

    @FXML
    public void handleNæste() {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(views.get(--currentView));
    }

    public void initialize() {
        booking = Booking.getInstance();

        // Get the views from the FXML files
        FXMLLoader view1Loader = new FXMLLoader(BookingApplication.class.getResource("BookingTypeGUI.fxml"));
        FXMLLoader view2Loader = new FXMLLoader(BookingApplication.class.getResource("ÅbenSkoleGUI.fxml"));
        FXMLLoader view3Loader = new FXMLLoader(BookingApplication.class.getResource("OrgTeacherGUI.fxml"));
        FXMLLoader view4Loader = new FXMLLoader(BookingApplication.class.getResource("BookingPageGUI.fxml"));
        FXMLLoader view5Loader = new FXMLLoader(BookingApplication.class.getResource("ConfirmBookingGUI.fxml"));

        LinkedList<FXMLLoader> loaderList = new LinkedList<>();
        loaderList.add(view1Loader);
        loaderList.add(view2Loader);
        loaderList.add(view3Loader);
        loaderList.add(view4Loader);
        loaderList.add(view5Loader);

        // Load the views into memory
        views = new LinkedList<>();
        try {
            for (FXMLLoader fxmlLoader : loaderList) {
                views.add(fxmlLoader.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Insert view1 into the stack pane by default
        currentView = 0;
        stackPane.getChildren().clear();
        stackPane.getChildren().add(views.get(currentView));
    }

}
