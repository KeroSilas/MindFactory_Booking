package group3.mindfactory_booking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("OpeningGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setWidth(700);
        stage.setHeight(600);
        stage.setMinWidth(700);
        stage.setMinHeight(600);
        stage.setTitle("Mind Factory Booking");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}