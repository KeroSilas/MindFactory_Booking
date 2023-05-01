package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.dao.BookingDao;
import group3.mindfactory_booking.dao.BookingDaoImpl;
import group3.mindfactory_booking.model.Booking;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.LinkedList;

public class NavigationController {

    private BookingDao bookingDao;
    private Booking booking;

    private LinkedList<FXMLLoader> loaderList;
    private LinkedList<BorderPane> views;
    private int currentViewIndex;

    @FXML
    private StackPane stackPane;

    @FXML
    private MFXButton forrigeButton, næsteButton;

    @FXML
    public void handleForrige() {
        // Switch to the previous view
        if (currentViewIndex != 0) {
            stackPane.getChildren().clear();
            stackPane.getChildren().add(views.get(--currentViewIndex));
        } else {
            // If the current view is the first view, go back to the opening view
            FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("OpeningGUI.fxml"));
            try {
                stackPane.getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleNæste() {
        // Add the next view to the list if it doesn't exist
        // This is done to prevent the views from being reloaded every time the user switches between views
        if (views.size() != loaderList.size() || currentViewIndex == views.size() - 1 && currentViewIndex != loaderList.size() - 1) {
            try {
                views.add(loaderList.get(currentViewIndex + 1).load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Switch to the next view
        if (currentViewIndex != loaderList.size() - 1) {
            stackPane.getChildren().clear();
            stackPane.getChildren().add(views.get(++currentViewIndex));
        } else {
            // If the current view is the last view, save the booking and go to the opening view
            //bookingDao.saveBooking(booking);
            FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("OpeningGUI.fxml"));
            try {
                stackPane.getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize() {
        bookingDao = new BookingDaoImpl();
        booking = Booking.getInstance();

        // Get the views from the FXML files
        FXMLLoader view1Loader = new FXMLLoader(BookingApplication.class.getResource("BookingTypeGUI.fxml"));
        FXMLLoader view2Loader = new FXMLLoader(BookingApplication.class.getResource("OpenSchoolGUI.fxml"));
        FXMLLoader view3Loader = new FXMLLoader(BookingApplication.class.getResource("OrgTeacherGUI.fxml"));
        FXMLLoader view4Loader = new FXMLLoader(BookingApplication.class.getResource("BookingPageGUI.fxml"));
        FXMLLoader view5Loader = new FXMLLoader(BookingApplication.class.getResource("ConfirmBookingGUI.fxml"));

        // Add the fxmlLoaders to the list
        loaderList = new LinkedList<>();
        loaderList.add(view1Loader);
        loaderList.add(view2Loader);
        loaderList.add(view3Loader);
        loaderList.add(view4Loader);
        loaderList.add(view5Loader);

        views = new LinkedList<>();
        // Load the first view into the list
        try {
            views.add(view1Loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Insert view1 into the stack pane by default
        currentViewIndex = 0;
        stackPane.getChildren().clear();
        stackPane.getChildren().add(views.get(currentViewIndex));
    }

}
