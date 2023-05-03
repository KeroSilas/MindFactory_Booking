package group3.mindfactory_booking.controllers.singleton;

import group3.mindfactory_booking.BookingApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.LinkedList;

// https://stackoverflow.com/questions/49067498/switching-between-scenes-in-java-fx-with-fxml-files-and-an-controller
// https://gist.github.com/pethaniakshay/302072fda98098a24ce382a361bdf477
public class PageManager {

    private final LinkedList<BorderPane> views;
    private int currentViewIndex;

    private static PageManager instance = null;

    private PageManager() {
        // Load the first view into the list
        views = new LinkedList<>();
        try {
            views.add(FXMLLoader.load(BookingApplication.class.getResource("bookingType-1-view.fxml")));
            views.add(FXMLLoader.load(BookingApplication.class.getResource("aabenSkole-2-view.fxml")));
            views.add(FXMLLoader.load(BookingApplication.class.getResource("udstyrOgAktivitet-2-view.fxml")));
            views.add(FXMLLoader.load(BookingApplication.class.getResource("informationOgDato-3-view.fxml")));
            views.add(FXMLLoader.load(BookingApplication.class.getResource("bekræftBooking-4-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Insert view1 into the stack pane by default
        currentViewIndex = 0;
    }

    public static PageManager getInstance() {
        if (instance == null) {
            instance = new PageManager();
        }
        return instance;
    }

    public BorderPane getNextPage() {
        return views.get(++currentViewIndex);
    }

    public BorderPane getPreviousPage() {
        return views.get(--currentViewIndex);
    }

    public BorderPane getCurrentPage() {
        return views.get(currentViewIndex);
    }

}
