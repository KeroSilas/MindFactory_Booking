package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.dao.BookingDao;
import group3.mindfactory_booking.dao.BookingDaoImpl;
import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.tasks.SaveBookingTask;
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
        // Switch to the next view
        if (currentViewIndex != views.size() - 1) {
            stackPane.getChildren().clear();
            stackPane.getChildren().add(views.get(++currentViewIndex));
        } else {
            // If the current view is the last view, save the booking and go to the opening view
            SaveBookingTask saveBookingTask = new SaveBookingTask();
            saveBookingTask.setOnSucceeded(event -> {
                if (saveBookingTask.getValue()) {
                    System.out.println("Booking saved successfully");

                    FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("OpeningGUI.fxml"));
                    try {
                        stackPane.getScene().setRoot(fxmlLoader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Booking failed to save");
                }
            });
            Thread thread = new Thread(saveBookingTask);
            thread.setDaemon(true);
            thread.start();
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
        LinkedList<FXMLLoader> loaderList = new LinkedList<>();
        loaderList.add(view1Loader);
        loaderList.add(view2Loader);
        loaderList.add(view3Loader);
        loaderList.add(view4Loader);
        loaderList.add(view5Loader);

        // Load the first view into the list
        views = new LinkedList<>();
        try {
            for (FXMLLoader loader : loaderList) {
                views.add(loader.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Insert view1 into the stack pane by default
        currentViewIndex = 0;
        stackPane.getChildren().clear();
        stackPane.getChildren().add(views.get(currentViewIndex));
    }

    // TODO: Implement this method
    // Check if the booking is valid by looking at what view the user is currently on
    // and then check if the user has filled out the required fields correctly via the Booking singleton
    // This method should be called whenever the user clicks the "Næste" button
    // If the booking is valid, the user should be able to go to the next view
    private boolean checkBooking() {
        switch (currentViewIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
        }
        return false; // TODO: Remove this line
    }

}
