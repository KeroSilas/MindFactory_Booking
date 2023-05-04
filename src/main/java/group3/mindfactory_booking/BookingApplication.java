package group3.mindfactory_booking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("homepage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setWidth(725);
        stage.setHeight(725);
        stage.setMinWidth(725);
        stage.setMinHeight(725);
        stage.setTitle("Mind Factory Booking");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}