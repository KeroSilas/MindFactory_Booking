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
        stage.setWidth(850);
        stage.setHeight(700);
        stage.setMinWidth(850);
        stage.setMinHeight(700);
        stage.setTitle("Mind Factory Booking");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}